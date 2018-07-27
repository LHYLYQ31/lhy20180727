/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.attachment.service;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.modules.attachment.dao.AttachmentDao;
import com.sinosoft.hms.modules.attachment.model.Attachment;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：附件管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentDao attachmentDao;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.attachment.service.AttachmentService#save(com.sinosoft.hms.modules.attachment.model.Attachment)
     */
    @Override
    public Attachment save(Attachment attachment) {
        attachmentDao.saveOrUpdate(attachment);
        return attachment;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.attachment.service.AttachmentService#getModel(com.sinosoft.hms.modules.attachment.model.Attachment)
     */
    @Override
    public Attachment getModel(Attachment attachment) {
        Attachment att = null;
        if (StringUtils.isNotBlank(attachment.getId())) {
            att = (Attachment) attachmentDao.getById(attachment.getId());
        }
        else if (StringUtils.isNotBlank(attachment.getFoodId())) {
            HqlHelper helper = new HqlHelper(Attachment.class);
            helper.addCondition("foodId=?", attachment.getFoodId());
            List<Attachment> list = (List<Attachment>) attachmentDao.queryList(helper);
            if (list != null && list.size() > 0) {
                att = list.get(0);
            }
        }
        return att;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.attachment.service.AttachmentService#list(java.lang.String)
     */
    @Override
    public List<Attachment> list(String foodId) {
        HqlHelper helper = new HqlHelper(Attachment.class);
        helper.addCondition("foodId=?", foodId);
        List<Attachment> list = (List<Attachment>) attachmentDao.queryList(helper);
        return list;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.attachment.service.AttachmentService#delete(java.lang.String)
     */
    @Override
    public Boolean delete(String id) {
        Boolean flag = false;
        Attachment att = (Attachment) attachmentDao.getById(id);
        if (att != null) {
            String url = GlobalNames.FILE_PATH;
            File file = new File(url + att.getFilePath());
            if (file.exists()) {
                if (file.delete()) {
                    attachmentDao.deleteById(id);
                    flag = true;
                }
            }

        }
        return flag;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.attachment.service.AttachmentService#update(com.sinosoft.hms.modules.attachment.model.Attachment)
     */
    @Override
    public Boolean update(Attachment att) {
        attachmentDao.saveOrUpdate(att);
        return true;
    }

}
