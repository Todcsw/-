package com.csw.huffmantree;

import java.io.*;
import java.util.*;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/28 20:11 星期二
 * @Description: com.csw.huffmantree 赫夫曼编码的压缩和解压
 * @version: 1.0
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果:" + Arrays.toString(huffmanCodeBytes) + "长度=" + huffmanCodeBytes.length);

        byte[] bytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串=" + new String(bytes));

        //测试压缩文件的代码
        zipFile("d://src.bmp", "d://src.hhh");
        //测试解压文件
        unZipFile("d://dst.zip","d://src2.bmp");


        /*分步过程
        List<Node1> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        System.out.println("测试创建的二叉树~~~~~哈夫曼树");
        Node1 root = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        root.preOrder();

        //测试生成了对应的赫夫曼编码
        getCodes(root);
        System.out.println("生成的赫夫曼编码表" + huffmanCodes);

        //测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
        */
    }


    /**
     * 编写一个方法,完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到那个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 编写方法,将一个文件进行压缩
     *
     * @param srcFile 你写入希望压缩文件的全路径
     * @param dstFile 压缩文件放入那个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输入输出流
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        try {
            is = new FileInputStream(srcFile);
            //创建一个和原文件大小一样的数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //读取文件对应赫夫曼编码表
            //直接对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流相关联的objectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的方式写进赫夫曼编码,为了我们恢复源文件时使用，
            //注意赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据的解压 思路
    //1.将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重写先转成赫夫曼对应的二进制的字符串“1010100010111...”
    //2.赫夫曼编码对应的二进制的字符串“1010100010111...”=》对照赫夫曼编码=》 “i like like like java do you like ”

    /**
     * 编写方法,完成对压缩数据的编码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes,对应的二进制字符串 形式1010100000
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(flag, b));
        }
        //把字符串按照指定的赫夫曼编码就行解码
        //把赫夫曼编码进行交换,因为反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合存放byte
        List<Byte> list = new ArrayList<>();
        //可以理解位索引
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //递增的取出
                String key = stringBuilder.substring(i, i + count); //i不动,让count移动,指定匹配道的字符
                b = map.get(key);
                if (b == null) {
                    //说明没有匹配道
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count; //i直接移动到count
        }
        //当for循环结束后,这个list存放了所有字符
        //把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成二进制的字符串
     *
     * @param b
     * @param flag 表示表示是否需要补高位
     * @return 返回该byte对应的 二进制字符串(补码)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b; //将b转成Int
        //如果是整数我们还需要补位
        if (flag) {
            temp |= 256; //temp按位与 10000 0000 |0000 0001=>
        }

        String str = Integer.toBinaryString(temp); //返回的是temp对应二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    /**
     * 使用一个方法,将前面的方法封装起来,便于我们的调用
     *
     * @param bytes 原始的字符串对应的字符串,便于我们调用
     * @return 是经过赫夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node1> nodes = getNodes(bytes);
        //根据nodes创建的赫夫曼树
        Node1 huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的赫夫曼编码(根据赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    /**
     * 编写一个方法,将字符串对应的byte[]数组,通过生成的赫夫曼编码表返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例:String content="i like like like java do you like a java"; ->byte[] contentBytes=> content.getBytes
     * 返回的是字符串对应的byte数组 =>即8个对应一个byte放到byte数组中
     * 10101000(补码)=> 10101000-1=>10100111(反码)=>11011000(原码)=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将""转成byte[]

        //统计返回byte[] huffmanCodeBytes长度
        //一句话
        //int len=(stringBuilder.length()+7)/8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //因为是每8位对应一个byte，所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路:将赫夫曼编码表存在map<Byte,String>形式
    //32->01 97->100 100->11000等等
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生成赫夫曼编码表时需要取拼接路径,定义一个StringBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便,重载getCodes
    private static Map<Byte, String> getCodes(Node1 root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能:将传入的Node结点的所有叶子结点的赫夫曼编码得到,并放入到
     *
     * @param node          传入结点,
     * @param code          路径:左子结点是0 右子结点1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node1 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //如果node==null不处理
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
                //非叶子结点
                //递归处理,向左,
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //说明是个叶子结点
                //表明找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历的一个方法
    private static void preOrder(Node1 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * @param bytes 接收一个字节数组
     * @return 返回的是一个list形式,
     */
    private static List<Node1> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node1> nodes = new ArrayList<>();
        //遍历bytes,统计每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转换成一个Node对象,并加入到nodes集合中
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node1(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //可以通过List创建对应的赫夫曼树
    private static Node1 createHuffmanTree(List<Node1> nodes) {
        while (nodes.size() > 1) {
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node1 leftNode = nodes.get(0);
            //取出第二棵最小的二叉树
            Node1 rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根结点,没有data只有权值
            Node1 parent = new Node1(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树,加入到nodes
            nodes.add(parent);
        }
        //返回的结点,最后的结点就是哈夫曼树的根结点
        return nodes.get(0);
    }
}

//创建Node,带数据和权值
class Node1 implements Comparable<Node1> {
    Byte data; //存放数据本身,比如'a'=>97 ''=>32
    int weight; //权值,表示字符的出现次数
    Node1 left; //
    Node1 right;

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node1 o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node1[" +
                "data=" + data + " weight=" + weight +
                ']';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
