<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link href="/public/css/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/public/css/element/index.css" rel="stylesheet">

    <script type="text/javascript" src="/public/js/vue.js"></script>
    <script type="text/javascript" src="/public/js/axios.js"></script>
    <script type="text/javascript" src="/public/js/element-ui/index.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/lodash.js/4.17.4/lodash.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/qs/6.5.1/qs.js"></script>
    <script type="text/javascript" src="/public/js/common-utils.js"></script>

    <style>
        .login-container {
            -webkit-border-radius: 5px;
            border-radius: 5px;
            -moz-border-radius: 5px;
            background-clip: padding-box;
            margin: 180px auto;
            width: 350px;
            padding: 35px 35px 15px 35px;
            background: #fff;
            border: 1px solid #eaeaea;
            box-shadow: 0 0 25px #cac6c6;
        }

        .login-container .title {
            margin: 0 auto 40px;
            text-align: center;
            color: #505458;
        }

        .login-container .remember {
            margin: 0 0 35px;
        }

        .impowerBox {
            line-height: 1.6;
            position: relative;
            width: 100%;
            z-index: 1;
            text-align: center;
        }

        .impowerBox .qrcode {
            width: 150px;
            height: 150px;
            margin-top: 15px;
            border: 1px solid #E2E2E2;
        }

    </style>
</head>
<body>
<div id="app">
    <el-form ref="loginForm" :model="loginForm" :rules="rules" label-position="left" label-width="0px"
             class="demo-ruleForm login-container">
        <h3 class="title">系统登录</h3>
        <el-form-item prop="account">
            <el-input type="text" v-model="loginForm.phoneOrEmail" placeholder="邮箱/手机号"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-checkbox checked class="remember">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
            <el-button type="primary" style="width:100%;" @click="submitForm" :loading="submitFormLoding">登录</el-button>
        </el-form-item>
    </el-form>
</div>
<script>

    function reloadPage() {
        var ifr = document.getElementById("mainframe");
        if (ifr) {
            ifr.contentWindow.document.location.reload();
        } else {
            if (location.pathname.indexOf("login") >= 0) {
                location.href = location.host;;
                location.reload()
            } else {
                location.pathname == "/logout" ? location.href = '/' : location.reload();
            }

        }
    }

    var vm = new Vue({
        el: '#app',
        data: {
            haveShowNotice: false,
            loginForm: {
                phoneOrEmail: '',
                password: ''
            },
            rules: {
                phoneOrEmail: Form.notBlankValidator("邮箱/手机号不能为空"),
                password: Form.notBlankValidator("密码不能为空")
            },
            submitFormLoding: false,
            showQrCode: false
        },
        methods: {
            submitForm: function () {
                var self = this;
                Form.validate(this, 'loginForm', function (resp) {
                    self.submitFormLoding = true;
                    Resource.post("/user/login.json", {
                        phoneOrEmail: self.loginForm.phoneOrEmail,
                        password: self.loginForm.password
                    }, function (resp) {
                        self.submitFormLoding = false;
                        if (resp.data.code != 0) {
                            Message.error(resp.msg);
                            return;
                        }
                        Message.success("登录成功");
                        window.location.href = "/index"
                        setTimeout(function () {
                            reloadPage();
                        }, 1000)
                    })

                })
            },
        }
    })
</script>
</body>
</html>