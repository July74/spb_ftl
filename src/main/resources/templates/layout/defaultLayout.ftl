<#macro layout>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/public/css/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/public/css/element/index.css" rel="stylesheet">
    <link href="/public/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="/public/js/vue.min.js"></script>
    <script type="text/javascript" src="/public/js/axios.min.js"></script>
    <script type="text/javascript" src="/public/js/element-ui/index.js"></script>
    <script type="text/javascript" src="/public/js/lodash.min.js"></script>
    <script type="text/javascript" src="/public/js/qs.js"></script>
    <script type="text/javascript" src="/public/js/common-utils.js"></script>
</head>
<body>
<div class="layout">
    <el-row type="flex">
        <el-col :span="spanLeft" :class="{'layout-hide-text':spanLeft < 3}" id="main">
            <#include "head.ftl">
            <#include "left.ftl">
        </el-col>
        <el-col :span="spanRight" :class="{'left-position':isCollapse}">
            <#include "head-right.ftl">
            <div class="layout-content" id="app">
                <div class="layout-content-main">
                    <#nested>
                </div>
            </div>
        </el-col>
    </el-row>
    <div style="position:fixed;left:0;right:0;bottom:0;z-index:10;text-align:center;color:#999;line-height:40px;background:#fff">
        <#include "foot.ftl">
    </div>
</div>
</body>
<script type="text/javascript">
    var vmMain = new Vue({
        el: '#main',
        data: {
            activeMenu: '',
            activeSubMenu: [],
            isCollapse: false
        },
        computed: {
            spanLeft: function () {
                return this.isCollapse ? 1 : 3
            },
            spanRight: function () {
                return this.isCollapse ? 23 : 21
            },
        },
        created: function () {
            var hash = window.location.hash.substr(1);
            if (hash) {
                var start = hash.indexOf("/");
                var hashIndex = hash.substring(0, start);
                var hashSubMenuIndex = hash.substring(start + 1, hash.length);
                this.activeMenu = hashIndex;
                this.activeSubMenu.push(hashIndex)
            }
        },
        methods: {
            handleSelect: function (key, keyPath) {
                if (key) {
                    window.location.href = "/" + keyPath[0] + "/" + key;
                }
            }, handleclose: function () {
                //console.log('handleclose');
            }, handleClick: function (command) {
                if (command == "logout") {
                    Message.confirm("确认退出吗?", function () {
                        Resource.post("/logout", {}, function (resp) {
                            //var resp = resp.data;
                            //Message.success("注销成功");
                            window.location.reload();
                        })
                    });
                }
            },
        }
    });
</script>

</html>
</#macro>