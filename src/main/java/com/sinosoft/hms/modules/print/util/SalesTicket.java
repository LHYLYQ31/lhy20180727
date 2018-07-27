/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.print.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.modules.order.vo.OrderInfo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：//实现Printable接口 用于创建打印内容 </B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public class SalesTicket implements Printable {
    //具体商品内容
    private List<OrderInfo> list;
    private Font font;
    //总价
    private String sale_sum;
    //订单号
    private String orders;
    //座位号
    private String number;

    // 构造函数  
    public SalesTicket(List<OrderInfo> list, String orders, String sale_sum,
            String number) {
        this.list = list;
        // 订单标号  
        this.orders = orders;
        // 总金额  
        this.sale_sum = sale_sum;
        //座位号
        this.number = number;
    }

    /**
     * @param Graphic指明打印的图形环境
     * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×
     *            842点）
     * @param pageIndex指明页号
     **/
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
        // 转换成Graphics2D 拿到画笔  
        Graphics2D g2 = (Graphics2D) graphics;
        // 设置打印颜色为黑色  
        g2.setColor(Color.black);

        // 打印起点坐标  
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();
        // 虚线  
        float[] dash1 = { 4.0f };
        // width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为  
        // 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。  
        // cap - BasicStroke 端点的装饰  
        // join - 应用在路径线段交汇处的装饰  
        // miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。  
        // dash - 表示虚线模式的数组  
        // dash_phase - 开始虚线模式的偏移量  
        // 设置画虚线  
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));
        // 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称） 
        //设置 标题离上边距    设置为15
        float line = 15.0f;
        font = new Font("宋体", Font.PLAIN, 16);
        g2.setFont(font);// 设置字体  
        float heigth = font.getSize2D();// 字体高度  
        line += heigth;
        // 标题  
        try {
            g2.drawString("序号:" + number, (float) x + 30, (float) y + line);
            line += heigth;
            g2.drawString(URLDecoder.decode(GlobalNames.SIGN_NAME, "utf-8"), (float) x + 30, (float) y + line);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        font = new Font("宋体", Font.PLAIN, 8);
        g2.setFont(font);// 设置字体  
        heigth = font.getSize2D();// 字体高度  
        line += 2 * heigth;
        // 显示订单号  和座位号
        g2.drawString("订单号:" + orders, (float) x + 10, (float) y + line);
        g2.drawString("座位号:" + number, (float) x + 90, (float) y + line);
        line += 2 * heigth;
        // 显示标题  
        g2.drawString("名称", (float) x + 10, (float) y + line);
        g2.drawString("单价", (float) x + 60, (float) y + line);
        g2.drawString("数量", (float) x + 90, (float) y + line);
        g2.drawString("总额", (float) x + 120, (float) y + line);
        line += heigth;
        //画虚线
        g2.drawLine((int) x, (int) (y + line), (int) x + 140, (int) (y + line));
        // 第4行  
        line += 2 * heigth;
        // 显示内容  
        for (int i = 0; i < list.size(); i++) {
            OrderInfo info = list.get(i);
            g2.drawString(info.getName(), (float) x + 10, (float) y + line);
            g2.drawString(info.getPrice(), (float) x + 60, (float) y + line);
            g2.drawString(info.getNum(), (float) x + 90, (float) y + line);
            Float totlePrice = Float.valueOf(info.getPrice()) * Float.valueOf(info.getNum());
            g2.drawString(totlePrice.toString(), (float) x + 120, (float) y + line);
            line += 2 * heigth;
        }
        g2.drawLine((int) x, (int) (y + line), (int) x + 140, (int) (y + line));
        line += 2 * heigth;
        g2.drawString("合计:" + sale_sum + "元", (float) x + 10, (float) y + line);
        /*
         * line += heigth;
         * g2.drawString("实收:" + practical + "元", (float) x, (float) y + line);
         */
        line += 2 * heigth;
        g2.drawString("时间:" + Calendar.getInstance().getTime().toLocaleString(), (float) x + 10, (float) y + line);
        line += 2 * heigth;
        // 显示序列号
        //        font = new Font("宋体", Font.PLAIN, 16);
        //        g2.setFont(font);
        //        heigth = font.getSize2D();// 字体高度  
        //        line += heigth;
        //        g2.drawString("序号:" + number, (float) x + 30, (float) y + line);
        line += 4 * heigth;
        // g2.drawLine((int) x, (int) (y + line), (int) x + 140, (int) (y + line));
        g2.drawString("- -欢迎光临本店- -", (float) x + 20, (float) y + line);

        switch (pageIndex) {
        case 0:
            return PAGE_EXISTS;
        default:
            return NO_SUCH_PAGE;

        }

    }

}
