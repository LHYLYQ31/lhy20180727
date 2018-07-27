/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.print.service;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.modules.number.model.HotelNumber;
import com.sinosoft.hms.modules.number.service.NumberService;
import com.sinosoft.hms.modules.order.model.Order;
import com.sinosoft.hms.modules.order.service.OrderService;
import com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService;
import com.sinosoft.hms.modules.order.vo.OrderInfo;
import com.sinosoft.hms.modules.print.util.SalesTicket;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@Service
public class PrintServiceImpl implements PrintService {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderVegetablesTypeService orderVegetablesTypeService;
    @Autowired
    NumberService numberService;

    private PageFormat initData() {

        // 设置成竖打  
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。  
        Paper paper = new Paper();
        paper.setSize(158, 30000);// 纸张大小  
        paper.setImageableArea(0, 0, 158, 30000);// A4(595 X  
        pf.setPaper(paper); // 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72  
        return pf;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.print.service.PrintService#print(java.lang.String)
     */
    @Override
    public void print(Long orderId) {
        // 通俗理解就是书、文档  
        Book book = new Book();
        PageFormat pf = initData();
        List<OrderInfo> list = orderVegetablesTypeService.list(String.valueOf(orderId));
        Order order = new Order();
        order.setId(orderId);
        Order selectOrder = orderService.getModel(order);
        HotelNumber num = numberService.getModel();
        //可以查询出来 桌号， 总价
        book.append(new SalesTicket(list, orderId.toString(), selectOrder.getTotalPrice(), num
                .getOrderNumber()),
                pf);
        // 获取打印服务对象  
        PrinterJob job = PrinterJob.getPrinterJob();
        // 设置打印类  
        job.setPageable(book);
        try {
            job.print();
            Integer value = Integer.valueOf(num.getOrderNumber()) + 1;
            num.setOrderNumber(String.valueOf(value));
            numberService.update(num);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
