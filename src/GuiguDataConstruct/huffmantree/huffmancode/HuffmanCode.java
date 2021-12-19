package GuiguDataConstruct.huffmantree.huffmancode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**哈夫曼编码压缩方法
 * 1. 文件本身是经过压缩处理过的，那么使用哈夫曼编码再压缩效率就不会明显（如视频、ppt）
 * 2. 哈夫曼编码是按字节来处理的，因此可以处理所有的文件
 * 3. 如果一个文件中的内容重复数据不多，压缩效果也不会太明显
 * @title: HuffmanCode
 * @Author Tan
 * @Date: 2021/12/18 15:46
 * @Version 1.0
 */
public class HuffmanCode {
    // 数据流程： String => byte[]（ASCII码表对应） => 通过byte[]构建哈夫曼树 => 通过哈夫曼树构建哈夫曼编码 => 利用哈夫曼编码将字节数组 byte[] 进行转化成新的字节数组 => 将新的字节数组进行二进制压缩 => 最终压缩后的数组

    /**
     * 文件压缩
     * @param scrFilePath：传入文件的路径
     * @param desFile： 压缩后的文件存放目录
     */
    public void ZipFile(String scrFilePath,String desFile){
        // 创建文件输入流 / 输出流
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(scrFilePath);
            // 创建一个和源文件大小一样的byte[]数组
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 获取文件对应的哈夫曼编码表 => 对文件进行压缩
            Map<Byte, String> huffmanCode =  getHuffmanCode(b);
            byte[] huffmanBytes =  huffmanZip(b);
            // 创建文件输出流存放压缩文件
            os = new FileOutputStream(desFile);
            // 创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 以对象流的方式写入哈夫曼字节数组和哈夫曼编码，为了以后恢复源文件时使用
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 文件解压
     * @param zipFile:准备解压的文件
     * @param desFile：文件解压的路径
     */
    public void unZipFile(String zipFile,String desFile){
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream oos = null;
        // 定义文件输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is 关联的对象输入流
            oos = new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) oos.readObject();
            // 读取哈夫曼编码表
            Map<Byte,String> huffmanCode = (Map<Byte, String>) oos.readObject();
            // 解码
            byte[] bytes =  huffmanUnzip(huffmanCode,huffmanBytes);
            // 写入目标文件中
            os = new FileOutputStream(desFile);
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                oos.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 字符串压缩
     * @param str
     * @return
     */
    public byte[] huffmanZip(String str){
        // 字符串转化为字节数组
        byte[] strByte =  getBytes(str);
        // 获取哈夫曼编码表
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree();
        huffmanCodeTree.createHuffmanCodeTree(strByte);
        StringBuilder PathCode = new StringBuilder();
        huffmanCodeTree.getHuffmanCode(huffmanCodeTree.root, "", PathCode);
        Map<Byte, String> huffmanCode = huffmanCodeTree.huffmanCode;
        // 将byte数组通过哈夫曼编码表进行压缩
        return zipdata(strByte,huffmanCode);
    }

    /**
     * 字符串压缩
     * @param b:原字符串对应的字节数组
     * @return： 压缩后的字节数组
     */
    public byte[] huffmanZip(byte[] b){
        // 获取哈夫曼编码表
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree();
        huffmanCodeTree.createHuffmanCodeTree(b);
        StringBuilder PathCode = new StringBuilder();
        huffmanCodeTree.getHuffmanCode(huffmanCodeTree.root, "", PathCode);
        Map<Byte, String> huffmanCode = huffmanCodeTree.huffmanCode;
        // 将byte数组通过哈夫曼编码表进行压缩
        return zipdata(b,huffmanCode);
    }

    /**
     * 解压方法
     * @param huffmancode: 哈夫曼编码表
     * @param huffmanBytes：哈夫曼编码压缩后的字节数组
     * @return： 原来字符串对应的字节数组
     */
    public byte[] huffmanUnzip(Map<Byte,String> huffmancode,byte[] huffmanBytes){
        // 1. 获得huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节(是的话 flag = true)
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        // 2. 将字符串按指定的哈夫曼编码进行解码( 根据哈夫曼编码在 '100011011010000000001100'中解码 )
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry: huffmancode.entrySet()){
            // 将哈夫曼编码表 “反转”
            map.put(entry.getValue(),entry.getKey());
        }
             // 创建集合存放byte()
        ArrayList<Byte> list = new ArrayList<>();
        // 解码过程 （字节码100 => 11000 逆过程） => 重要
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                // 取出一个‘1’，‘0’
                // i 不动， count 移动，直到匹配到一个字符 => 双指针寻找匹配的key
                String key = stringBuilder.substring(i,i + count);
                // 在逆哈夫曼表中寻找对应的字符 ”a“
                b = map.get(key);
                if (b == null){
                    // 没找到，count 增大继续寻找
                    count++;
                }else {
                    // 找到了，本次循环结束，将 key 加入集合中，i 继续移动
                    flag = false;
                }
            }
            // 将 "a" 放入集合中
            list.add(b);
            // i 跳到 count 的位置，下一次循环开始后 i = i + 1从 count 的后一个位置开始查找
            i += count;
        }
        // 循环结束后 list 中就存放了所有的字符 ”i like like like java do you like a java“
        // 将list的数据放入byte[] 中并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;

    }

    /**
     * 将一个Byte转成一个二进制的字符串
     * @param flag: 是否需要补高位（True）
     * @param b: 传入的byte
     * @return: b对应的二进制字符串
     */
    public String byteToBitString(boolean flag,byte b){
        int temp = b;
        // 按位与
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }

    /**方法一：String => byte[]（ASCII码表对应）
     * @param str：输入的字符串
     * @return： 原字符串对应的字节数组
     */
    public byte[] getBytes(String str){
        return str.getBytes();
    }

    /**
     * 方法二： 获取源文件字节数组对应的哈夫曼编码表
     * @param bytes： 源文件字节数组
     * @return： 哈夫曼编码表
     */
    public Map<Byte, String> getHuffmanCode(byte[] bytes){
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree();
        // 创建哈夫曼树
        huffmanCodeTree.createHuffmanCodeTree(bytes);
        // 获取哈夫曼编码表
        StringBuilder PathCode = new StringBuilder();
        huffmanCodeTree.getHuffmanCode(huffmanCodeTree.root, "", PathCode);
        Map<Byte, String> huffmanCode = huffmanCodeTree.huffmanCode;
       return huffmanCode;
    }


    /**方法三：将输入byte数组通过哈夫曼编码表进行压缩
     * 利用哈夫曼编码将字节数组 byte[] 进行转化成新的字节数组 => 将新的字节数组进行二进制压缩
     * @param bytes: 输入byte数组
     * @param huffmanCode 哈夫曼编码表
     * @return： 经过哈夫曼编码压缩后的byte数组
     */
    public  byte[] zipdata(byte[] bytes, Map<Byte,String> huffmanCode){
        // 1. 利用 huffmanCode 将 bytes 转化成 "0110011000...."
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        // 2. 将生成的编码字符串转化成 byte 数组并压缩
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的 byte 数组 (8 位对应一个 byte)
        byte[] huffmanByte = new byte[len];
        int index = 0;    // 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strbyte;
            if (i + 8 > stringBuilder.length()){
                // 最后的字符不够 8 位
                strbyte = stringBuilder.substring(i);
            }else {
                // 8 位一取
                strbyte = stringBuilder.substring(i, i + 8);
            }
            // 将strbyte转成一个 byte,放入 huffmanByte 中
            huffmanByte[index] = (byte) Integer.parseInt(strbyte,2);
            index++;
        }
        return huffmanByte;

    }

}

