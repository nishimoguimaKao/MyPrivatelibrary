<template>
  <div>
    <el-menu
      :default-active="this.$route.path"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red"
      style="min-width: 1300px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name" >
        {{ item.navItem }}
      </el-menu-item>
        <a href="#nowhere" @click="openDrawer" style="color: #222;float: right;padding: 20px;" v-if="this.$route.path==='/admin'">修改密码</a>
      <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">Book Sharing——Let Thoughts Fly</span>
    </el-menu>
    <el-drawer
      title="修改密码"
      size="30%"
      :visible.sync="drawer"
      :direction="direction"
      :before-close="handleClose">
      <el-form v-model="form" label-width="50px" style="text-align: left" ref="dataForm">
        <el-form-item label="原密码:" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.password_1" autocomplete="off" placeholder="请输入原密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码:" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.password_2" autocomplete="off" placeholder="请输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认新密码:" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.password_3" autocomplete="off" placeholder="请再次输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="title">
          <el-button type="primary" @click="submit">确认</el-button>
          <el-button type="success" @click="clearItems">清空</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: 'NavMenu',
  data() {
    return {
      form: {
        password_1: '',
        password_2: '',
        password_3: ''
      },
      user: {
        id: null,
        username: '',
        password: ''
      },
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/library', navItem: '图书馆'},
        {name: '/admin', navItem: '个人中心'}
      ],
      drawer: false,
      direction: 'rtl',
      formLabelWidth: '120px'
    }
  },
  methods: {
    openDrawer() {
      this.form.password_1 = ''
      this.form.password_2 = ''
      this.form.password_3 = ''
      this.drawer = true
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    submit() {
      this.user.id = window.sessionStorage.getItem("userid")
      this.user.username = window.sessionStorage.getItem("username")
      this.user.password = window.sessionStorage.getItem("userPassword")
      if (this.form.password_1 === '') {
        this.$alert('请输入原密码！')
      } else if (this.form.password_2 === '') {
        this.$alert('请输入新密码！')
      } else if (this.form.password_3 === '') {
        this.$alert('请确认新密码！')
      } else if (this.form.password_2 !== this.form.password_3) {
        this.$alert('两次输入的密码不一样！')
      } else {
        if (this.user.password === this.form.password_1) {
          console.log("密码相等，进入")
          this.user.password = this.form.password_3
          console.log("前端修改密码成功")
          this.$axios.post("/updatePassword", this.user)
            .then(function (res) {
              //成功
              console.log("请求发送成功")
              window.sessionStorage.setItem("userPassword", this.form.password_3)
              console.log("重新设置userPassword的sessionStorage成功")
              this.form.password_1 = ''
              this.form.password_2 = ''
              this.form.password_3 = ''
              console.log("清空成功")
              this.$message({
                message: '密码修改成功！',
                type: 'success'
              })
              this.drawer = false
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        } else {
          this.$message.error("原密码不正确!请重新输入！")
          this.form.password_1 = ''
        }
      }
    },
    clearItems() {
      this.form.password_1 = ''
      this.form.password_2 = ''
      this.form.password_3 = ''
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}

span {
  pointer-events: none;
}

.el-input {
  width: 350px;
}

</style>
