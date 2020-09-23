<template>
  <body id="poster">
  <el-form class="login-container" label-position="left"
           label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox-group v-model="checkedItem">
      <el-checkbox v-for="(value,index) in projectData" :label="value.name"
                   @change="chooseItem(value.id)">{{ value.name }}
      </el-checkbox>
    </el-checkbox-group>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
      <el-button type="primary" style="width: 50%;background: #505458;border: none" v-on:click="register">注册</el-button>
    </el-form-item>
  </el-form>
  <el-drawer
    title="注册"
    style="text-align: center"
    size="30%"
    :visible.sync="drawer"
    :direction="direction"
    :before-close="handleClose"
    append-to-body>
    <el-form v-model="form" label-width="50px" style="text-align: left" ref="dataForm">
      <el-form-item label="用户名:" :label-width="formLabelWidth" prop="title">
        <el-col span="18">
          <el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="密码:" :label-width="formLabelWidth" prop="title">
        <el-col span="18">
          <el-input v-model="form.password_1" autocomplete="off" placeholder="请输入密码" show-password></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="确认密码:" :label-width="formLabelWidth" prop="title">
        <el-col span="18">
          <el-input v-model="form.password_2" autocomplete="off" placeholder="请再次输入密码" show-password></el-input>
        </el-col>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" prop="title">
        <el-button type="primary" @click="submit">确认</el-button>
        <el-button type="success" @click="clearItems">清空</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
  </body>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        id: null,
        username: '',
        password: ''
      },
      user: {
        id: null,
        username: '',
        password: ''
      },
      form: {
        username: '',
        password_1: '',
        password_2: ''
      },
      checkedItem: [],
      projectData: [{name: '记住用户名', id: 1}, {name: '记住密码', id: 2}],
      drawer: false,
      direction: 'rtl',
      formLabelWidth: '120px',
      usernameRemembered: 0,
      usernamePasswordRemembered: 0,
      responseResult: [],

    }
  },
  methods: {
    /*
     * 1.点击登录按钮，向后端发送数据
     * 2.受到后端返回的成功代码时，触发 store 中的 login() 方法，把 loginForm 对象传递给 store 中的 user 对象
     *  （*这里只是简单的实现，在后端我们可以通过用户名和密码查询数据库，获得 user 表的完整信息，比如用户昵称、用户级别等，返回前端，并传递给 user 对象，以实现更复杂的功能）
     * 3.获取登录前页面的路径并跳转，如果该路径不存在，则跳转到首页
     */
    login() {
      if (this.loginForm.username === '') {
        this.$message.warning("请输入用户名！")
      } else if (this.loginForm.password === '') {
        this.$message.warning("请输入密码！")
      } else {
        var _this = this
        console.log(this.$store.state)
        if (this.usernameRemembered === 1) {
          console.log("usernameRemembered")
          window.sessionStorage.setItem("usernameRemembered", this.loginForm.username)
        }
        if (this.usernamePasswordRemembered === 1) {
          console.log("usernamePasswordRemembered")
          window.sessionStorage.setItem("usernamePasswordRemembered", this.loginForm.password)
        }
        this.$axios.get('/' + this.loginForm.username)
          .then(res => {
            this.loginForm = res.data
            console.log(this.loginForm)
          })
        this.$axios.post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(successResponse => {//successResponse=>if(...){...}相当于 function(successResponse){if(...){...}}
          console.log(successResponse.data.code)
          if (successResponse.data.code === 200) {
            // var data = this.loginForm
            this.$message.success("登陆成功!")
            _this.$store.commit('login', _this.loginForm)
            var path = this.$route.query.redirect
            window.sessionStorage.setItem("userid", _this.loginForm.id)
            window.sessionStorage.setItem("username", _this.loginForm.username)
            window.sessionStorage.setItem("userPassword", _this.loginForm.password)
            this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
          } else {
            this.$message.error("用户名或密码错误！请重新输入！")
          }
        }).catch(failResponse => {

        })
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    register() {
      this.drawer = true
    },
    submit() {
      if (this.form.username === '') {
        this.$alert('请输入用户名！')
      } else if (this.form.password_1 === '') {
        this.$alert('请输入密码！')
      } else if (this.form.password_2 === '') {
        this.$alert('请确认密码！')
      } else if (this.form.password_1 !== this.form.password_2) {
        this.$alert('两次输入的密码不一样！')
      } else {
        console.log("密码相等")
        this.$axios.get("/" + this.form.username)
          .then(function (res) {
            //成功
            console.log(res.data)
            console.log("请求成功")
            if (res.data === '') {
              console.log("用户名未重复")
              this.user.username = this.form.username
              this.user.password = this.form.password_2
              this.$axios.post('/addUser', this.user)
                .then(res => {
                  this.$message({
                    message: '用户创建成功！',
                    type: 'success'
                  })
                }).bind(this).catch(error => {
                if (error.response) {
                  console.log(error.response)
                }
              })
              console.log("请求发送成功")
              this.form.username = ''
              this.form.password_1 = ''
              this.form.password_2 = ''
              this.$message({
                message: '用户创建成功！',
                type: 'success'
              })
              this.drawer = false
              this.form.username = ''
              this.form.password_1 = ''
              this.form.password_2 = ''
            } else {
              this.$message.error("用户名重复，请重新输入用户名！")
              this.form.username = ''
            }
          }.bind(this))
          .catch(function (err) {
            if (err.response) {
              console.log(err.response)
            }
          }.bind(this))
      }
    },
    clearItems() {
      this.form.username = ''
      this.form.password_1 = ''
      this.form.password_2 = ''
    },
    chooseItem(id) {
      if (id === 1) {
        console.log("记住用户名")
        if (window.sessionStorage.getItem("usernameRemembered") === null) {
          console.log("空")
          console.log(this.checkedItem)
          window.sessionStorage.setItem("usernameRemembered", this.loginForm.username)
        } else {
          console.log("非空")
          console.log(this.checkedItem)
          window.sessionStorage.removeItem("usernameRemembered")
        }
      } else if (id === 2) {
        console.log("记住密码")
        if (window.sessionStorage.getItem("usernamePasswordRemembered") === null) {
          console.log("空")
          window.sessionStorage.setItem("usernamePasswordRemembered", this.loginForm.password)
        } else {
          console.log("非空")
          window.sessionStorage.removeItem("usernamePasswordRemembered")
        }
      }
    }
  },
  created: function () {
    window.sessionStorage.getItem("usernameRemembered") === null ? this.loginForm.username = '' : this.loginForm.username = window.sessionStorage.getItem("usernameRemembered")
    window.sessionStorage.getItem("usernamePasswordRemembered") === null ? this.loginForm.password = '' : this.loginForm.password = window.sessionStorage.getItem("usernamePasswordRemembered")
    window.sessionStorage.removeItem("usernameRemembered")
    window.sessionStorage.removeItem("usernamePasswordRemembered")
  }
}
</script>

<style scoped>
.login-container {
  border-radius: 20px;
  background-clip: padding-box;
  margin: 250px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

#poster {
  background: url("../assets/login_background.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0px;
}
</style>
