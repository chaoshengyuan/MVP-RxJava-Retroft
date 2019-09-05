package com.wenbing.mvpdemo.mvp.factory;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;

/**
 * @author gs_wenbing
 * @date 2019/9/3 17:48
 */
public class IPresenterMvpFactoryImpl<V extends BaseMvpView, P extends BaseMvpPresenter<V>> implements IPresenterMvpFactory<V, P> {

    private final Class<P> mPresenterClass;

    private IPresenterMvpFactoryImpl(Class<P> pClass) {
        this.mPresenterClass = pClass;
    }

    public static <V extends BaseMvpView, P extends BaseMvpPresenter<V>> IPresenterMvpFactoryImpl<V, P> createFactory(Class<?> viewClass) {
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        return pClass == null ? null : new IPresenterMvpFactoryImpl<>(pClass);
    }

    @Override

    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }


}
