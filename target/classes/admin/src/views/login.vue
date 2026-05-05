<template>
	<div>
		<div class="login-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form class="login_form animate__animated animate__">
				<div class="login_form2">
					<div class="title-container">图书馆座位预约平台登录</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号：
						</div>
						<input placeholder="请输入账号：" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input placeholder="请输入密码：" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item " v-if="roles.length>1&&loginType<=2">
						<div class="lable">
							角色：
						</div>
						<div prop="loginInRole" class="list-type">
							<el-radio v-if="loginType==1||(loginType==2&&item.role!='users')" v-for="item in roles" v-bind:key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
						</div>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1||loginType==3||loginType==4" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('gongzuorenyuan')" class="register">
								注册工作人员							</el-button>
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
				indexBgUrl: '',
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {
			this.$http.get('config/info?name=bLoginBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		},
		destroyed() {
		},
		components: {
		},
		methods: {

			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {
				if(this.loginType==1) {

					if (!this.rulesForm.username) {
						this.$message.error("请输入用户名");
						return;
					}
					if (!this.rulesForm.password) {
						this.$message.error("请输入密码");
						return;
					}
					if(this.roles.length>1) {
						if (!this.rulesForm.role) {
							this.$message.error("请选择角色");
							return;
						}
					
						for (let i = 0; i < this.roles.length; i++) {
							if (this.roles[i].roleName == this.rulesForm.role) {
								this.tableName = this.roles[i].tableName;
							}
						}
					} else {
						this.tableName = this.roles[0].tableName;
						this.rulesForm.role = this.roles[0].roleName;
					}
				}
		
				this.loginPost()
			},
			loginPost() {
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'yonghu') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'gongzuorenyuan') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					}
					else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20250722/3e242061ebd5475888f6a6872062493c.jpg);
	background-repeat: no-repeat !important;
	background-size: 100% 100% !important;
	background: url(http://codegen.caihongy.cn/20250722/3e242061ebd5475888f6a6872062493c.jpg);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: flex-end;
	background-position: center top;
	position: relative !important;

	.login_form {
		padding: 0;
		margin: 0 auto;
		z-index: 1000;
		display: flex;
		min-height: 100vh;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: inset 0px 0px 0px 0px #000;
		background: none;
		width: 100%;
		justify-content: center;
		align-items: flex-end;
		height: auto;
		.login_form2 {
			padding: 10px 6%;
			margin: 0 0 10px;
			align-content: center;
			background: rgba(255,255,255,.8);
			display: flex;
			width: 42%;
			min-height: 50vh;
			align-items: center;
			flex-wrap: wrap;
		}
		.title-container {
			padding: 15px 0 30px;
			margin: 0 0 0px 0;
			color: #fff;
			top: 80px;
			left: 29%;
			background: url(http://codegen.caihongy.cn/20250722/63e304b255d54256ac4be204cd9bc791.png) no-repeat center 85%,rgba(0, 0, 0, .5);
			font-weight: 600;
			width: 42%;
			font-size: 24px;
			line-height: 40px;
			position: absolute;
			text-align: center;
		}
		.list-item {
			border-radius: 30px;
			padding: 0 0px;
			margin: 0 auto 20px;
			background: none;
			display: flex;
			width: 100%;
			align-items: center;
			.lable {
				color: #333;
				width: 80px;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				line-height: 44px;
				border-style: solid;
				text-align: left;
				height: 44px;
			}
			input {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			input:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.password-box {
				flex: 1;
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border-radius: 0px;
					padding: 0 10px;
					color: #333;
					background: none;
					flex: 1;
					width: 100%;
					font-size: 16px;
					border-color: #acacac;
					border-width: 0 0 2px;
					border-style: solid;
					height: 44px;
				}
				input:focus {
					border-radius: 0px;
					padding: 0 10px;
					color: #4c7bf5;
					flex: 1;
					background: none;
					width: 100%;
					font-size: 16px;
					border-color: #acacac;
					border-width: 0 0 2px;
					border-style: solid;
					height: 44px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #666;
				font-size: 16px;
			}
		}
		.list-type {
			padding: 0 0 0 20px;
			margin: 0 auto;
			width: 100%;
			border-color: #acacac;
			border-width: 0 0 2px;
			line-height: 44px;
			border-style: solid;
			height: 44px;
			/deep/ .el-radio__input .el-radio__inner {
				background: rgba(53, 53, 53, 0);
				border-color: #666666;
			}
			/deep/ .el-radio__input.is-checked .el-radio__inner {
				background: linear-gradient(111.31deg, rgba(123, 79, 251, 1),rgba(222, 61, 249, 1) 100%);
				border-color: #8256fc;
			}
			/deep/ .el-radio__label {
				color: #666666;
				font-size: 16px;
			}
			/deep/ .el-radio__input.is-checked+.el-radio__label {
				color: #8256fc;
				font-size: 16px;
			}
		}
		.login-btn {
			margin: 20px auto 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.login-btn1 {
				width: 100%;
			}
			.login-btn2 {
				width: 100%;
				text-align: right;
				order: 5;
			}
			.login-btn3 {
				margin: 0;
				width: 100%;
			}
			.loginInBt {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 24px;
				margin: 0 0 15px;
				outline: none;
				color: #fff;
				background: linear-gradient(111deg, rgba(123, 79, 251, 1),rgba(222, 61, 249, 1) 100%);
				width: 100%;
				font-size: 24px;
				height: 50px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 0px solid #333;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 10px;
				margin: 0 0 10px;
				outline: none;
				color: #000;
				background: none;
				width: auto;
				font-size: 16px;
				height: auto;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 10px;
				margin: 0;
				outline: none;
				color: #000;
				background: none;
				width: auto;
				font-size: 16px;
				height: auto;
			}
			.forget:hover {
				opacity: 0.8;
			}
		}
	}
}

</style>
