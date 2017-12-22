<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.layout>

<#-- 将下面这个main content嵌入到layout指令的nested块中 -->
<!--工具条-->
<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
    <el-form :model="queryForm" ref="queryForm" :inline="true">
        <el-form-item label="搜索">
            <el-input type="text" v-model="queryForm.title" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-button type="primary" @click="getData"><i class="el-icon-search"></i> 查询</el-button>
        <el-button type="primary" @click="resetForm('queryForm')">重置</el-button>
        <el-button type="primary" @click="add">添加页面分享配置</el-button>
    </el-form>
</el-col>
<!--列表-->
<el-table border :data="tableData" highlight-current-row v-loading="tableLoading" @selection-change="selsChange">
    <el-table-column prop="userId" label="序号" width="100px"></el-table-column>
    <el-table-column align="center" prop="userName" label="姓名"></el-table-column>
    <el-table-column align="center" prop="mobile" label="手机"></el-table-column>
    <el-table-column align="center" prop="activeDate" label="时间"></el-table-column>

    <el-table-column label="操作" align="center">
        <template scope="scope">
            <el-button @click="edit(scope.row)" size="small" type="primary">修改</el-button>
            <el-button @click="deleteItem(scope.row)" size="small" type="danger">删除</el-button>
        </template>
    </el-table-column>
</el-table>
<!--工具条-->
    <el-col :span="24" class="toolbar">
        <el-pagination @current-change="pageChange" :current-page="queryForm.page"
                       :total="total" layout="total, prev, pager, next, jumper" style="float:right;">
        </el-pagination>
    </el-col>

<script type="text/javascript">
    var vm = new Vue({
        el: '#app',
        data: {
            queryForm: {
                title: '',
                pageIndex: 1,
                pageSize: 10,
            },
            total: 0,
            tableData: [],
            tableLoading: false,
            showDialog: false,
            rules: {}
        },
        created: function () {
            this.getData();
        },
        methods: {
            getData: function () {
                var that = this;
                that.tableLoading = true;
                Resource.get("/user/list.json", this.queryForm, function (resp) {
                    that.tableData = resp.data.data.data;
                    that.total = resp.data.data.total;
                    that.tableLoading = false;
                });
            },
            pageChange: function (page) {
                this.queryForm.pageIndex = page;
                this.getData();
            },
            resetForm: function (formName) {
                Form.resetFields(this, formName);
            },
            add: function () {
                window.location.href = "/user/add"
            },
            edit: function (row) {
                window.location.href = "/user/edit"
            },
            deleteItem: function (row) {
                var that = this;
                var ids = row.id;
                Message.confirm("确认删除?" + row.userName, function () {
                    // Resource.post("/user/delete", {ids: ids}, function (data) {
                    //     that.$message({
                    //         message: '删除成功',
                    //         type: 'success'
                    //     });
                    //     that.getData();
                    // })
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            }
        }
    });
</script>
</@defaultLayout.layout>