<el-menu :default-active="activeMenu" :default-openeds="activeSubMenu" @close="handleclose" unique-opened @select="handleSelect"
         class="el-menu-vertical-demo" :collapse="isCollapse">
    <el-submenu index="user">
        <template slot="title">
            <i class="fa fa-cogs"></i>
            <span slot="title" class="layout-text">用户管理</span>
        </template>
        <el-menu-item index="list" class="layout-text">用户列表</el-menu-item>
    </el-submenu>
    <el-submenu index="order">
        <template slot="title">
            <i class="fa fa-bar-share"></i>
            <span slot="title" class="layout-text">订单管理</span>
        </template>
        <el-menu-item index="list" class="layout-text">订单列表</el-menu-item>
        <el-menu-item index="check" class="layout-text">审核订单</el-menu-item>
    </el-submenu>
</el-menu>
