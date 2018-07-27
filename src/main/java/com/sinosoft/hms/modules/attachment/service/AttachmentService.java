/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.attachment.service;

import java.util.List;

import com.sinosoft.hms.modules.attachment.model.Attachment;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
public interface AttachmentService {
    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存附件信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:31:49
     * @param attachment
     * @return Attachment
     */
    Attachment save(Attachment attachment);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：查询附件信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:32:32
     * @param attachment
     * @return Attachment
     */

    Attachment getModel(Attachment attachment);

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：根据菜品id查询附件信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:34:49
     * @param foodId 菜品id
     * @return List<Attachment>
     */
    List<Attachment> list(String foodId);

    /**
     * 
     * <B>方法名称：delete</B><BR>
     * <B>概要说明：删除附件信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:34:18
     * @param id 附件id
     * @return Boolean
     */
    Boolean delete(String id);

    Boolean update(Attachment att);

}
