<template>
	<div>
		<div class="register-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">图书馆座位预约平台</div>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('xuehao')?'required':''">学号：</div>
						<el-input  v-model="ruleForm.xuehao" :readonly="ro.xuehao" autocomplete="off" placeholder="学号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
						<el-input  v-model="ruleForm.xingming" :readonly="ro.xingming" autocomplete="off" placeholder="姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in yonghuxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="yonghutouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
						<el-input  v-model="ruleForm.shoujihaoma" :readonly="ro.shoujihaoma" autocomplete="off" placeholder="手机号码"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('xueyuan')?'required':''">学院：</div>
						<el-input  v-model="ruleForm.xueyuan" :readonly="ro.xueyuan" autocomplete="off" placeholder="学院"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('banji')?'required':''">班级：</div>
						<el-input  v-model="ruleForm.banji" :readonly="ro.banji" autocomplete="off" placeholder="班级"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('yuangonggonghao')?'required':''">员工工号：</div>
						<el-input  v-model="ruleForm.yuangonggonghao" :readonly="ro.yuangonggonghao" autocomplete="off" placeholder="员工工号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('yuangongxingming')?'required':''">员工姓名：</div>
						<el-input  v-model="ruleForm.yuangongxingming" :readonly="ro.yuangongxingming" autocomplete="off" placeholder="员工姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in gongzuorenyuanxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="gongzuorenyuantouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gongzuorenyuan'">
						<div class="lable" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input  v-model="ruleForm.lianxifangshi" :readonly="ro.lianxifangshi" autocomplete="off" placeholder="联系方式"  type="text"  />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
			ro: {},
            yonghuxingbieOptions: [],
            gongzuorenyuanxingbieOptions: [],
			indexBgUrl: '',
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='yonghu'){
				this.ruleForm = {
					xuehao: '',
					mima: '',
					xingming: '',
					xingbie: '',
					touxiang: '',
					shoujihaoma: '',
					xueyuan: '',
					banji: '',
				}
				this.ro = {
					xuehao: false,
					mima: false,
					xingming: false,
					xingbie: false,
					touxiang: false,
					shoujihaoma: false,
					xueyuan: false,
					banji: false,
				}
			}
			if(this.tableName=='gongzuorenyuan'){
				this.ruleForm = {
					yuangonggonghao: '',
					mima: '',
					yuangongxingming: '',
					xingbie: '',
					touxiang: '',
					lianxifangshi: '',
					sfsh: '',
					shhf: '',
				}
				this.ro = {
					yuangonggonghao: false,
					mima: false,
					yuangongxingming: false,
					xingbie: false,
					touxiang: false,
					lianxifangshi: false,
					sfsh: false,
					shhf: false,
				}
			}
			if ('yonghu' == this.tableName) {
				this.rules.xuehao = [{ required: true, message: '请输入学号', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.shoujihaoma = [{ required: true, message: '请输入手机号码', trigger: 'blur' }]
			}
			if ('gongzuorenyuan' == this.tableName) {
				this.rules.yuangonggonghao = [{ required: true, message: '请输入员工工号', trigger: 'blur' }]
			}
			if ('gongzuorenyuan' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('gongzuorenyuan' == this.tableName) {
				this.rules.yuangongxingming = [{ required: true, message: '请输入员工姓名', trigger: 'blur' }]
			}
			this.yonghuxingbieOptions = "男,女".split(',')
			this.gongzuorenyuanxingbieOptions = "男,女".split(',')
		}
	},
	created() {
		this.$http.get('config/info?name=bRegisterBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        yonghutouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        gongzuorenyuantouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.xuehao) && `yonghu` == this.tableName){
				this.$message.error(`学号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `yonghu` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `yonghu` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.xingming) && `yonghu` == this.tableName){
				this.$message.error(`姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.shoujihaoma) && `yonghu` == this.tableName){
				this.$message.error(`手机号码不能为空`);
				return
			}
			if(`yonghu` == this.tableName && this.ruleForm.shoujihaoma &&(!this.$validate.isMobile(this.ruleForm.shoujihaoma))){
				this.$message.error(`手机号码应输入手机格式`);
				return
			}
			  if(this.tableName=='gongzuorenyuan'){
				  this.ruleForm.sfsh = '待审核'
			  }
			if((!this.ruleForm.yuangonggonghao) && `gongzuorenyuan` == this.tableName){
				this.$message.error(`员工工号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `gongzuorenyuan` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `gongzuorenyuan` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.yuangongxingming) && `gongzuorenyuan` == this.tableName){
				this.$message.error(`员工姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`gongzuorenyuan` == this.tableName && this.ruleForm.lianxifangshi &&(!this.$validate.isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		},
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20250722/3e242061ebd5475888f6a6872062493c.jpg);
	background-repeat: no-repeat !important;
	background-size: 100% 100% !important;
	background: url(http://codegen.caihongy.cn/20250722/3e242061ebd5475888f6a6872062493c.jpg);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center bottom;
	.rgs-form {
		.rgs-form2 {
		padding: 20px 1% 10px;
		margin: 0 0 10px;
		align-content: center;
		background: rgba(255,255,255,.8);
		display: flex;
		width: 50%;
		min-height: 50vh;
		align-items: center;
		flex-wrap: wrap;
		}
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
		.title {
			padding: 15px 0 30px;
			margin: 0 0 0px 0;
			color: #fff;
			top: 80px;
			left: 25%;
			background: url(http://codegen.caihongy.cn/20250722/63e304b255d54256ac4be204cd9bc791.png) no-repeat center 85%,rgba(0, 0, 0, .5);
			font-weight: 600;
			width: 50%;
			font-size: 24px;
			line-height: 40px;
			position: absolute;
			text-align: center;
		}
		.list-item {
			border-radius: 30px;
			padding: 0 0 0 130px;
			margin: 0 0 15px;
			background: none;
			width: 48%;
			position: relative;
			height: auto;
			/deep/ .el-form-item__content {
				display: block;
			}
			.lable {
				padding: 0 10px 0 0;
				color: #333;
				left: -130px;
				width: 130px;
				font-size: 16px;
				line-height: 44px;
				position: absolute !important;
				text-align: right;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #acacac;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 2px solid #acacac;
				cursor: pointer;
				border-radius: 0px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 2px solid #acacac;
				cursor: pointer;
				border-radius: 0px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 2px solid #acacac;
				cursor: pointer;
				border-radius: 0px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 16px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #123;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 120px;
				position: absolute;
				content: "*";
			}
			.editor {
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				flex: 1;
				background: none;
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
			input::placeholder {
				color: #123;
				font-size: 16px;
			}
			button {
				border: 0;
				cursor: pointer;
				padding: 0 0px;
				margin: 0;
				color: #333;
				font-size: 16px;
				border-color: #acacac;
				border-radius: 0 0px 0px 0;
				box-shadow: none;
				outline: none;
				background: none;
				width: 130px;
				border-width: 0 0 2px;
				border-style: solid;
				height: 44px;
			}
			button:hover {
				color: #4c7bf5;
				border-color: #acacac;
			}
		}
		.register-btn {
			width: 100%;
		}
		.register-btn1 {
			padding: 0;
			width: 100%;
			text-align: center;
		}
		.register-btn2 {
			padding: 0;
			margin: 0;
			width: 100%;
			text-align: center;
		}
		.r-btn {
			border: 0;
			cursor: pointer;
			border-radius: 0px;
			padding: 0 20px;
			margin: 10px auto;
			outline: none;
			color: #fff;
			background: linear-gradient(111deg, rgba(123, 79, 251, 1),rgba(222, 61, 249, 1) 100%);
			width: 450px;
			font-size: 22px;
			height: 50px;
		}
		.r-btn:hover {
			opacity: 0.5;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #333;
			display: inline-block;
			font-size: 16px;
			line-height: 2;
		}
		.r-login:hover {
			opacity: 0.8;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
