const Message = (function () {
    vm = new Vue();

    function success(msg) {
        if (!msg) msg = "成功!";
        vm.$message.success(msg)
    }

    function error(msg) {
        if (!msg) msg = "出错!";
        vm.$message.error(msg)
    }

    function warn(msg) {
        if (!msg) msg = "出错啦!";
        vm.$message.warn(msg)
    }

    function loading(msg) {
        if (!msg) msg = "正在加载中";
        vm.$message.loading({
            content: msg,
            duration: 0
        });
    }

    function confirm(msg, success, cancel) {
        if (!msg) msg = "注意!";

        vm.$confirm(msg, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(function () {
            if (success) success();
        }).catch(function () {
            if (cancel) cancel();
        });
    }

    return {
        success: success,
        error: error,
        warn: warn,
        loading: loading,
        confirm: confirm
    }
})();

const Form = (function () {

    /*这里之所以这么写，是因为不想出现必填的红色星号*/
    function notBlankValidator(msg) {
        return {
            validator: function (rule, value, callback) {
                if (!(value && (value + "").trim())) {
                    return callback(new Error(msg));
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    function checkStrLength(msg, minLen, maxLen) {
        return {
            validator: function (rule, value, callback) {
                if (value.length < minLen || value.length > maxLen) {
                    return callback(new Error(msg));
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    function isEnglish(msg) {
        return {
            validator: function (rule, value, callback) {
                for (i = 0, length = value.length; i < length; i++) {
                    if (value.charCodeAt(i) > 255) {
                        return callback(new Error(msg));
                    }
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    /**
     * 非负数
     * @param msg
     * @returns {{validator: validator, trigger: string}}
     */
    function nonNegativeNumber(msg) {
        return {
            validator: function (rule, value, callback) {
                if (isNaN(value) || value < 0 || value % 1 !== 0) {
                    return callback(new Error(msg));
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    function isHttpHref(msg) {
        return {
            validator: function (rule, value, callback) {
                if (value.indexOf("http://") !== 0 && value.indexOf("https://") !== 0) {
                    return callback(new Error(msg));
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    function isNullOrHttpHref(msg) {
        return {
            validator: function (rule, value, callback) {
                if (value.length > 0 && value.indexOf("http://") !== 0 && value.indexOf("https://") !== 0) {
                    return callback(new Error(msg));
                }
                callback();
            },
            trigger: 'blur',
        }
    }

    function isCommaSeparated(msg) {
        return {
            validator: function (rule, value, callback) {
                // 同时输入中英文逗号是不被允许的
                if (value.indexOf(",") > 0 && value.indexOf("，") > 0) {
                    callback(new Error(msg));
                }
                else {
                    callback();
                }
            },
            trigger: 'blur'
        }
    }

    function resetFields(vue, formName) {
        if (vue.$refs[formName]) {
            if (vue.$refs[formName].resetFields) vue.$refs[formName].resetFields();
            let model = vue.$refs[formName].model;
            if (model) {
                for (let key in model) model[key] = "";
            }
        }
    }

    /**必须提供[]2个元素*/
    function convertDateRange(dateRange) {
        if (!dateRange || dateRange.length === 0 || !dateRange[0] || !dateRange[1]) {
            return "";
        }
        format = function (d) {
            return d.getFullYear() + "-" +
                ("0" + (d.getMonth() + 1)).slice(-2) + "-" +
                ("0" + d.getDate()).slice(-2) + " " +
                ("0" + d.getHours()).slice(-2) + ":" +
                ("0" + d.getMinutes()).slice(-2) + ":" +
                ("0" + d.getSeconds()).slice(-2);
        };
        return format(dateRange[0]) + " ~ " + format(dateRange[1]);
    }

    /**
     * 验证表单，如果验证成功，调用success回调，否则调用error回调
     */
    function validate(vue, formName, success, error) {
        vue.$refs[formName].validate(function (valid) {
            if (!valid) {
                if (error) error();
                return false;
            }
            success();
        });
    }

    return {
        notBlankValidator: notBlankValidator,
        checkStrLength: checkStrLength,
        isEnglish: isEnglish,
        isCommaSeparated: isCommaSeparated,
        isHttpHref: isHttpHref,
        isNullOrHttpHref: isNullOrHttpHref,
        resetFields: resetFields,
        convertDateRange: convertDateRange,
        nonNegativeNumber: nonNegativeNumber,
        validate: validate
    }
})();

// 挂载到window实例下
window.Message = Message;

// ==========  axios : http client  ==========
const Resource = (function () {

    function get(url, params, success, error) {
        axios.get(url, {
            params: params
        }).then(function (response) {
            success(response)
        }).catch(function (err) {
            if (error) {
                error(err)
            } else {
                Message.error(err.message)
            }
        });
    }

    function post(url, params, success, error) {
        axios.post(url, params, {
            transformRequest: [function (data) {
                return Qs.stringify(data)
            }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function (response) {
            success(response)
        }).catch(function (err) {
            if (error) {
                error(err)
            } else {
                Message.error(err.message)
            }
        });
    }

    return {
        post: post,
        get: get
    }
})();