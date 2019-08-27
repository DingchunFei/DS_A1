package com.fei.gui;
import com.fei.client.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI
{
    public static void main(String[] agrs)
    {
        JFrame frame=new JFrame("MyDictionary");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建面板

        JTextField txtfield1=new JTextField(8);
        txtfield1.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式


        JTextField txtfield2=new JTextField(8);
        txtfield2.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式

        JTextField txtfield3=new JTextField(8);
        txtfield3.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式

        JTextField txtfield4=new JTextField(8);
        txtfield4.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式


        JLabel label1 = new JLabel("Query a word");
        JLabel label2 = new JLabel("Add a word");
        JLabel label3 = new JLabel("Remove a word");


        JButton btn1=new JButton("find");    //创建JButton对象
        JButton btn4=new JButton("reset");    //创建JButton对象
        JButton btn2=new JButton("add");    //创建JButton对象
        JButton btn3=new JButton("remove");    //创建JButton对象

        JTextArea jta1=new JTextArea("",7,30);
        jta1.setEditable(false);
        jta1.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta1.setForeground(Color.BLACK);    //设置组件的背景色
        jta1.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式
        jta1.setBackground(Color.WHITE);    //设置按钮背景色
        JScrollPane jsp1=new JScrollPane(jta1);    //将文本域放入滚动窗口
        Dimension size=jta1.getPreferredSize();    //获得文本域的首选大小
        jsp1.setBounds(110,90,size.width,size.height);

        JTextArea jta2=new JTextArea("",1,30);
        jta2.setEditable(false);
        jta2.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta2.setForeground(Color.BLACK);    //设置组件的背景色
        jta2.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式
        jta2.setBackground(Color.WHITE);    //设置按钮背景色
        JScrollPane jsp2=new JScrollPane(jta2);    //将文本域放入滚动窗口
        jsp2.setBounds(110,90,size.width,size.height);

        JTextArea jta3=new JTextArea("",1,30);
        jta3.setEditable(false);
        jta3.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta3.setForeground(Color.BLACK);    //设置组件的背景色
        jta3.setFont(new Font(Font.SERIF,Font.BOLD,16));    //修改字体样式
        jta3.setBackground(Color.WHITE);    //设置按钮背景色
        JScrollPane jsp3=new JScrollPane(jta3);    //将文本域放入滚动窗口
        jsp3.setBounds(110,90,size.width,size.height);

        jp.add(label1);
        jp.add(txtfield1);
        jp.add(btn1);
        jp.add(btn4);

        jp.add(jsp1);    //将JScrollPane添加到JPanel容器中

        jp.add(label2);
        jp.add(txtfield2);
        jp.add(txtfield3);
        jp.add(btn2);
        jp.add(jsp2);    //将JScrollPane添加到JPanel容器中

        jp.add(label3);
        jp.add(txtfield4);
        jp.add(btn3);
        jp.add(jsp3);    //将JScrollPane添加到JPanel容器中




        frame.add(jp);
        frame.setBounds(300,200,400,380);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Client client = new Client();

        //btn1.addActionListener();
        btn1.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
                 {
                     String result="";
                     System.out.println(txtfield1.getText());
                     try {
                         result = client.find(txtfield1.getText());
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                     jta1.setText(result);
                 }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String result="";
                System.out.println(txtfield2.getText());
                try {
                    result = client.add(txtfield2.getText(),txtfield3.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                jta2.setText(result);
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String result="";
                System.out.println(txtfield4.getText());
                try {
                    result = client.remove(txtfield4.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                jta3.setText(result);
            }
        });

        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               jta1.setText("");
               txtfield1.setText("");
            }
        });
    }
}