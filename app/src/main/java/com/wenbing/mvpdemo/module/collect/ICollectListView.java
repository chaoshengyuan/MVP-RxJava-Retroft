/**
 * Copyright (C), 2015-2020, XXX有限公司
 * Author: zwb
 * Date: 2020/4/4 15:04
 * Description: 啊啊
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.wenbing.mvpdemo.module.collect;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ArticleBean;

/**
 * @Author: zwb
 * @Date: 2020/4/4 15:04
 */
public interface ICollectListView extends IBaseView {
    void showData(ArticleBean article, int action);
}