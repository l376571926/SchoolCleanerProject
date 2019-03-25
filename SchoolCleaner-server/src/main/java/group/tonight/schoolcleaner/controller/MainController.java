package group.tonight.schoolcleaner.controller;

import group.tonight.schoolcleaner.jpa.BuyerMessageJPA;
import group.tonight.schoolcleaner.jpa.CollectionJPA;
import group.tonight.schoolcleaner.jpa.GoodJPA;
import group.tonight.schoolcleaner.jpa.UserJPA;
import group.tonight.schoolcleaner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private final UserJPA userJPA;
    private final GoodJPA goodJPA;
    private final CollectionJPA collectionJPA;
    private final BuyerMessageJPA buyerMessageJPA;

    @Autowired
    public MainController(UserJPA userJPA, GoodJPA goodJPA, CollectionJPA collectionJPA, BuyerMessageJPA buyerMessageJPA) {
        this.userJPA = userJPA;
        this.goodJPA = goodJPA;
        this.collectionJPA = collectionJPA;
        this.buyerMessageJPA = buyerMessageJPA;
    }

    @PostMapping(value = "/register")
    public BaseResponseBean register(String userPhone, String userPassword) {
        UserBean userBean = this.userJPA.findUserBeanByUserPhone(userPhone);
        if (userBean != null) {
            return new BaseResponseBean(-1, "用户已存在");
        } else {
            UserBean save = new UserBean();
            save.setUserPhone(userPhone);
            save.setUserPassword(userPassword);
            userJPA.save(save);
        }
        return new BaseResponseBean();
    }

    @PostMapping(value = "/login")
    public BaseResponseBean login(String userPhone, String userPassword) {
        UserBean userBean = this.userJPA.findUserBeanByUserPhone(userPhone);
        if (userBean == null) {
            return new BaseResponseBean(-1, "用户不存在");
        }
        String userPassword1 = userBean.getUserPassword();
        if (!userPassword1.equals(userPassword)) {
            return new BaseResponseBean(-1, "密码错误");
        }
        Long id = userBean.getId();
        System.out.println(id);
        DataResponseBean<Long> dataResponseBean = new DataResponseBean<>();
        dataResponseBean.setData(id);
        return dataResponseBean;
    }

    @PostMapping(value = "/sellGood")
    public BaseResponseBean sellGood(RequestGoodBean requestGoodBean) {
        Long userId = requestGoodBean.getFromUserId();
        Optional<UserBean> userBean = userJPA.findById(userId);
        if (!userBean.isPresent()) {
            return new BaseResponseBean(-1, "用户不存在");
        }
        GoodBean goodBean = new GoodBean();
        goodBean.setFromUserId(userId);
        goodBean.setGoodName(requestGoodBean.getGoodName());
        goodBean.setGoodPrice(requestGoodBean.getGoodPrice());
        goodBean.setGoodDescription(requestGoodBean.getGoodDescription());
        goodBean.setGoodAddress(requestGoodBean.getGoodAddress());
        goodJPA.save(goodBean);
        return new BaseResponseBean();
    }

    @PostMapping(value = "/goodList")
    public BaseResponseBean goodList(String searchKey) {
        ListResponseBean<GoodBean> goodBeanListResponseBean = new ListResponseBean<>();
        if (searchKey == null) {
            List<GoodBean> goodBeanList = goodJPA.findAll();
            goodBeanListResponseBean.setData(goodBeanList);
        } else {
            List<GoodBean> goodBeanList = goodJPA.findByGoodNameLike("%" + searchKey + "%");
            goodBeanListResponseBean.setData(goodBeanList);
        }
        return goodBeanListResponseBean;
    }

    @PostMapping(value = "/goodDetail")
    public BaseResponseBean goodDetail(Long goodId) {
        Optional<GoodBean> optionalGoodBean = goodJPA.findById(goodId);
        if (!optionalGoodBean.isPresent()) {
            new BaseResponseBean(-1, "物品不存在");
        }
        DataResponseBean<GoodBean> dataResponseBean = new DataResponseBean<>();
        optionalGoodBean.ifPresent(dataResponseBean::setData);
        return dataResponseBean;
    }

    @PostMapping(value = "/deleteGood")
    public BaseResponseBean deleteGood(Long goodId) {
        Optional<GoodBean> optionalGoodBean = goodJPA.findById(goodId);
        if (!optionalGoodBean.isPresent()) {
            new BaseResponseBean(-1, "物品不存在");
        }
        goodJPA.deleteById(goodId);
        return new BaseResponseBean();
    }

    @PostMapping(value = "/collectGood")
    public BaseResponseBean collectGood(Long userId, Long goodId) {
        if (!userJPA.existsById(userId)) {
            return new BaseResponseBean(-1, "用户不存在");
        }
        Optional<GoodBean> goodBean = goodJPA.findById(goodId);
        if (!goodJPA.existsById(goodId) || !goodBean.isPresent()) {
            return new BaseResponseBean(-1, "物品不存在");
        }
        GoodBean goodBean1 = goodBean.get();
        Long userId1 = goodBean1.getFromUserId();
        if (userId.equals(userId1)) {
            return new BaseResponseBean(-1, "不能收藏自己发布的物品");
        }
        if (collectionJPA.existsByUserIdAndGoodId(userId, goodId)) {
            return new BaseResponseBean(-1, "当前物品已被收藏");
        }

        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setUserId(userId);
        collectionBean.setGoodId(goodId);
        collectionJPA.save(collectionBean);
        return new BaseResponseBean();
    }

    @PostMapping(value = "/collectGoodList")
    public BaseResponseBean collectGoodList(Long userId) {
        List<CollectionBean> collectionBeanList = collectionJPA.findByUserId(userId);
        if (collectionBeanList.isEmpty()) {
            return new BaseResponseBean();
        }
        ListResponseBean<GoodBean> listResponseBean = new ListResponseBean<>();
        List<GoodBean> goodBeanList = new ArrayList<>();
        for (CollectionBean collectionBean : collectionBeanList) {
            Long goodId = collectionBean.getGoodId();
            Optional<GoodBean> byId = goodJPA.findById(goodId);
            byId.ifPresent(goodBeanList::add);
        }
        listResponseBean.setData(goodBeanList);
        return listResponseBean;
    }

    @PostMapping(value = "/buyerMessage")
    public BaseResponseBean buyerMessage(Long goodId, Long userId, String message) {
        if (!goodJPA.existsById(goodId)) {
            return new BaseResponseBean(-1, "物品不存在");
        }
        if (!userJPA.existsById(userId)) {
            return new BaseResponseBean(-1, "用户不存在");
        }
        BuyerMessageBean buyerMessageBean = new BuyerMessageBean();
        buyerMessageBean.setGoodId(goodId);
        buyerMessageBean.setBuyerUserId(userId);
        buyerMessageBean.setMessage(message);

        buyerMessageJPA.save(buyerMessageBean);
        return new BaseResponseBean();
    }

    @PostMapping(value = "/buyerMessageList")
    public BaseResponseBean buyerMessageList(Long goodId) {
        List<BuyerMessageBean> messageBeanList = buyerMessageJPA.findByGoodId(goodId);
        ListResponseBean<BuyerMessageBean> listResponseBean = new ListResponseBean<>();
        listResponseBean.setData(messageBeanList);
        return listResponseBean;
    }

    @PostMapping(value = "/replyBuyerMessage")
    public BaseResponseBean replyBuyerMessage(Long messageId, String replyContent) {
        Optional<BuyerMessageBean> optional = buyerMessageJPA.findById(messageId);
        if (!buyerMessageJPA.existsById(messageId) || !optional.isPresent()) {
            return new BaseResponseBean(-1, "消息不存在");
        }
        BuyerMessageBean buyerMessageBean = optional.get();
        buyerMessageBean.setReplyContent(replyContent);
        buyerMessageJPA.save(buyerMessageBean);
        return new BaseResponseBean();

    }

}
