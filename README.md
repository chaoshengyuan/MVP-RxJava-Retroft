使用架构的目的是为使程序模块化，做到模块内的高聚合和模块间的低耦合，架构和模式并不是让你的代码减少，可能往往会增大，但是它帮你在逻辑上更简洁了，很好的定义了单一性原则，提供了更好的扩展性，方便定位问题以及后续开发中需求变跟时不至于满篇的去改一大堆东西 。（一下图片均来自网络，侵删）

### MVC简述

首先熟悉下在Android开发中，传统的MVC模式（view、model、controller）。

![](https://github.com/gs-wenbing/MVP-RxJava-Retroft/blob/master/image/mvc.jpg)

​															

Activity、Fragment、布局xml就相当于View层。业务模型，建立的数据结构，以及网络请求、数据库处理、I/O操作相当于model层。至于controller层就有点模糊了，我们大家都将Activity、Fragment视作controller层，也就是把view和controller都揉和在Activity和Fragment中，**视图显示和控制逻辑错综复杂**。

这样经过几次需求变跟、代码迭代后一个Activity动辄上千行代码，最主要的修改某个页面时控制逻辑也得跟着变动，当然了可能会出现的bug以及后续开发人员阅读代码的难度也就增加了很多，这就违背了架构的低耦合。

### MVP简述

这样更优的架构模式MVP也就得到更多开发者的青睐。

MVP中每个单词具体代表的含义是什么？

M层 和MVC中的model一样，都是模型层，负责处理存储、检索、操作数据，包括网络请求、数据库处理以及IO操作。

V层 和MVC的view一样，都是视图层，负责绘制UI元素以及与用户的交互，对应的是activity、Fragment、adapter、xml

P层 （Presenter）是真个MVP的控制中心，作为View和Model的中间枢纽，处理View和Model间的交互和业务逻辑

 **这样通过Presenter将View与Model进行隔离，使得View和Model之间不存在耦合，同时也将业务逻辑从View中抽离 ，视图层专注View的展示和用户的交互。**

![MVP](https://github.com/gs-wenbing/MVP-RxJava-Retroft/blob/master/image/MVP.jpg)

这样做的优势也就是显而易见了

-  **Model层与View层完全分离，修改view层不会影响Model层，降低了耦合**

- **复杂的逻辑处理放在presenter进行处理，减少了Activity的臃肿**

- **可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑， 提高了代码复用 ，代码灵活性**

-  **模块职责划分明显 ， 具有良好的可扩展性** 

-  **Presenter层与View层的交互是通过接口来进行的，便于单元测试。**

当然MVP也是有缺点的。

视图和Presenter的交互会过于频繁，使得他们的联系过于紧密。也就是说，一旦视图变更了，presenter也要变更。

### MVP 存在的问题

**1、接口过多问题**

使用MVP模式去构建项目，会造成类文件和接口文件的过多，进而增大包的体积。

解决方式：写一个Contract接口，然后把与MVP相关接口全部列入到里面去。

**2、内存泄漏**

当用户关闭了View层，但这时Model层如果仍然在进行耗时操作，因为Presenter层也持有View层的引用，所以造成垃圾回收器无法对View层进行回收，这样一来，就造成了内存泄漏。

解决方式：可以重写onDestroy()方法，在View销毁时强制回收掉Presenter；或是采用弱引用的方式

### MVP例子

#### 基类

从上面说明中，我们知道MVP有三次View层、Presenter层、Model层构成，在构建MVP架构的时候需考虑到代码的复用性已经以后的可扩展性，这样我们先设计基类。

基类BaseActivity、基类BaseFragment，所有activity和fragment的基类，

基类BasePresenter，所有Presenter的基类

接口IBaseView， 说明了每一个View基本需要的一些操作 

这样主要的一些基类就设计好了

![mvp_base](https://github.com/gs-wenbing/MVP-RxJava-Retroft/blob/master/image/mvp_base.png)

##### BaseView

定义每一个View基本需要的一些操作

```java
public interface IBaseView {

    void showLoading();

    void hideLoading();

    void showError(String msg);
}
```



##### BasePresenter

因为Presenter将View与Model进行隔离，也将业务逻辑从View中抽离 。

1、Presenter处理的数据需要回显到View上时，就需要持有View的引用，也就是Presenter需要 绑定 View 和解绑 View  （`onAttachView(V view)` `onDetachView() `）。所有的View都是IBaseView的子类， 所以必须要传入一个泛型的 View 层接口 。

2、View展现的数据需要从Model层获取，而Presenter是他们的桥梁，所有Presenter也需要持有Model层引用。

```java
public class BasePresenter<V extends IBaseView> {
    //用于及时取消订阅，以防止内存泄漏
    private CompositeDisposable mDisposable;

    protected ApiServer mApiServer = ApiServer.getInstance();

    private SoftReference <V> mReferenceView;

    /**
     * activity 创建的时关联presenter
     * @param view
     */
    public void onAttachView(V view) {
        this.mReferenceView = new SoftReference<>(view);
    }

    /**
     * activity 关闭时解除绑定,防止内存泄漏
     */
    public void onDetachView() {
        this.mReferenceView.clear();
        this.mReferenceView = null;
        removeDisposable();
    }

    public V getMvpView() {
        return this.mReferenceView.get();
    }

    protected void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.add(disposable);
        }
    }

    protected void removeDisposable() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}

```

在BasePresenter中用到了SoftReference，这样就更加严谨，防止内存泄漏。

##### BaseActivity

##### BaseActivity

```java
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutID());
        initViews();
        initViewListener();
    }

    /**
     * 初始化布局文件
     * @return  返回布局文件的xml
     */
    protected abstract int initLayoutID();

    /**
     * 初始化View控件
     */
    protected abstract void initViews();

    /**
     * 初始化View的事件
     */
    protected abstract void initViewListener();
```

上面的BaseActivity就是我们在MVC里面用到的基类，但在MVP架构中，需要实现IBaseView，同时要初始化Presenter以及绑定Presenter。

```java
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    private MaterialDialog mDialog;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutID());
        initPresenter();
        initViews();
        initViewListener();
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {
        mPresenter = createrPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    protected abstract P createrPresenter();

    /**
     * 初始化布局文件
     *
     * @return 返回布局文件的xml
     */
    protected abstract int initLayoutID();

    /**
     * 初始化View控件
     */
    protected abstract void initViews();

    /**
     * 初始化View的事件
     */
    protected abstract void initViewListener();

    @Override
    public void showLoading(String msg) {
        if (mDialog != null) {
            mDialog = mDialog.getBuilder().title(msg).build();
            mDialog.show();
        } else {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(this, msg, true);
            mDialog = builder.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }
    }

```

这样简单的基类代码就完成了。

#### 具体的用法

那到底具体怎么用呢？定义一个简单的按钮，然后点击请求网络数据，然后显示。

定义一个Activity 继承BaseActivity，实现IMainView接口，对应的MainPresenter里面处理请求网络，结果回显给页面。

MainActivity：

```java
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    TextView textView;
    Button btnSuccess;

    @Override
    protected MainPresenter createrPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        textView = findViewById(R.id.textView);
        btnSuccess = findViewById(R.id.btn_success);
    }

    @Override
    protected void initViewListener() {
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestData();
            }
        });
    }

    @Override
    public void showData(List<Banner> banners) {
        textView.setText(new Gson().toJson(banners));
    }
}
```

IMainView：

```java
public interface IMainView extends IBaseView {
    void showData(List<Banner> banners);
}
```

MainPresenter：

```java
public class MainPresenter extends BasePresenter<IMainView> {

    void requestData() {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getBanner(),
                new BaseObserver<List<Banner>>(getView(), "加载中...") {
                    @Override
                    protected void onSuccess(List<Banner> banners) {
                        if (getView() != null) {
                            getView().showData(banners);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {
                            getView().showError(ex.getMessage());
                        }
                    }

                }));
    }
}
```

这样显示整个例子就完成了，效果如下：

<img src="https://github.com/gs-wenbing/MVP-RxJava-Retroft/blob/master/image/Screenshot_20200304_143119_com.wenbing.mvpdemo.jpg" alt="Screenshot_20200304_143114_com.wenbing.mvpdemo" style="zoom:30%;" />


