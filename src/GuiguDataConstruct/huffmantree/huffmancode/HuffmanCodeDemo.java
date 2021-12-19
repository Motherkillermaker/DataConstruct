package GuiguDataConstruct.huffmantree.huffmancode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**步骤：
 * 1. 创建结点 Node{data(存放数据)，weight（权值），left,right}
 * 2. 得到要传数据（文本）对应的 byte[]数组 => ASCII码表对应
 * 3. 编写方法，构建哈夫曼树
 * 4. 获取哈夫曼编码（向左为0，向右为1）
 * 5. 利用哈夫曼编码表转化字符串生成字节数组
 * 6. 将字节数组进行压缩
 * 数据流程： String => byte[]（ASCII码表对应） => 通过byte[]构建哈夫曼树 => 通过哈夫曼树构建哈夫曼编码 => 利用哈夫曼编码将字节数组 byte[] 进行转化成新的字节数组 => 将新的字节数组进行二进制压缩（最终压缩后的数组）
 * @title: HuffmanCodeDemo
 * @Author Tan
 * @Date: 2021/12/18 13:22
 * @Version 1.0
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        System.out.println("***********************测试字符串转化位字节数组********************");
        byte[] strBytes = str.getBytes();
        System.out.println(Arrays.toString(strBytes));
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree();
        // 创建哈夫曼树
        huffmanCodeTree.createHuffmanCodeTree(strBytes);
        System.out.println("***********************测试哈夫曼的前序遍历***********************");
        // 前序遍历哈夫曼树
        huffmanCodeTree.preOrder();
        System.out.println("***********************测试获取哈夫曼编码*************************");
        // 获取哈夫曼编码
        StringBuilder PathCode = new StringBuilder();
        huffmanCodeTree.getHuffmanCode(huffmanCodeTree.root, "", PathCode);
        Map<Byte, String> huffmanCode = huffmanCodeTree.huffmanCode;
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            System.out.println("字节码" + entry.getKey() + " => " + entry.getValue());

        }
        System.out.println("***********************测试哈夫曼编码**********************");
        // 将字符串通过哈夫曼编码表转化为byte[]数组
        byte[] huffmanCodeBytes  = zipdata(strBytes,huffmanCode);
        System.out.println(Arrays.toString(huffmanCodeBytes));

        System.out.println("***********************测试哈夫曼编码(方法整合)**********************");
        HuffmanCode huffmanCode1 =  new HuffmanCode();
        byte[] huffmanCodeBytes1 = huffmanCode1.huffmanZip(str);
        System.out.println(Arrays.toString(huffmanCodeBytes1));

        System.out.println("***********************测试哈夫曼解码(方法整合)**********************");
        byte[] unzipRes = huffmanCode1.huffmanUnzip(huffmanCode,huffmanCodeBytes);
        System.out.println("编码之前(字节数组) " + Arrays.toString(strBytes));
        System.out.println("解码之后(字节数组) " +  Arrays.toString(unzipRes));
        System.out.println("原始字符串   " + str);
        System.out.println("解码后字符串  " + new String(unzipRes) );

        System.out.println("***********************测试压缩文件(方法整合)**********************");
//        String srcPath = "d://Uninstall.xml";
//        String desPath = "D://dst.zip";
//        huffmanCode1.ZipFile(srcPath,desPath);
//        System.out.println("压缩文件成功 ！");


        System.out.println("***********************测试解压文件文件(方法整合)**********************");
//        String zipFile = "D://dst.zip";
//        String desFile = "D://res.xml";
//        huffmanCode1.unZipFile(zipFile,desFile);
//        System.out.println("文件解压成功 ！");

    }

    /**
     * 将输入byte数组进行压缩
     * @param bytes
     * @param huffmanCode
     * @return
     */
    public static byte[] zipdata(byte[] bytes,Map<Byte,String> huffmanCode){
        // 1. 利用 huffmanCode 将 bytes 转化
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        // 2. 将生成的编码字符串转化成 byte 数组
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

