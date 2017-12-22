
var behaviorId = '';
var pageId = '';
var behaviorDefine = {
    enter_page: '进入页面',
    exit_page: '退出页面',
    button_click: '按钮点击',
    share: '分享'
};
pageDefine = {
    index: '首页',
    article_list: '作品列表',
    exchange_log: '兑换记录',
    rule: '详细规则'
};
buttonDefine = {
    get_score: '获取积分',
    exchange_log: '兑换记录',
    rule: '详细规则',
    share_friend: '发送给朋友',
    share_timeline: '分享到朋友圈'
};

function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

function getCookie(c_name) {
    if (document.cookie.length > 0){
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1){
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return "";
}

function init() {
    behaviorId = getCookie("behaviorId");
    if (behaviorId === '') {
        behaviorId = uuid();
        setCookie("behaviorId", behaviorId);
    }
    pageId = uuid();
}

function handleUserBehavior(behaviorCode, pageCode, buttonCode, buttonName) {
    console.log((new Date()).getTime());
    var params = {
        operationTime: (new Date()).getTime(),
        behaviorId: behaviorId,
        behaviorCode: behaviorCode,
        behaviorName: behaviorDefine[behaviorCode],
        pageId: pageId,
        pageCode: pageCode,
        pageName: pageDefine[pageCode]
    };

    if (buttonCode !== undefined) {
        var btnName = buttonDefine[buttonCode];
        if (btnName === undefined) {
            btnName = buttonName;
        }

        params.buttonCode = buttonCode;
        params.buttonName = btnName;
    }

    Resource.post("/api/user_behavior", params, function (resp) {
        console.log(resp);
    }, function (err) {
        console.log(err);
    });
}

init();
