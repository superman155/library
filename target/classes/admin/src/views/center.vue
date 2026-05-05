<template>
	<div :style='{"padding":"50px 30px 30px"}'>
		<el-form
			:style='{"border":"1px solid #00000050","padding":"40px 30px","boxShadow":"0px 4px 3px #8558ff50","borderRadius":"10px","flexWrap":"wrap","background":"#ffffff","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yonghu'"  label="学号" prop="xuehao">
					<el-input v-model="ruleForm.xuehao" :readonly="ro.xuehao" placeholder="学号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yonghu'"  label="姓名" prop="xingming">
					<el-input v-model="ruleForm.xingming" :readonly="ro.xingming" placeholder="姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='yonghu'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in yonghuxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='yonghu'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="yonghutouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yonghu'"  label="手机号码" prop="shoujihaoma">
					<el-input v-model="ruleForm.shoujihaoma" :readonly="ro.shoujihaoma" placeholder="手机号码" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yonghu'"  label="学院" prop="xueyuan">
					<el-input v-model="ruleForm.xueyuan" :readonly="ro.xueyuan" placeholder="学院" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yonghu'"  label="班级" prop="banji">
					<el-input v-model="ruleForm.banji" :readonly="ro.banji" placeholder="班级" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='gongzuorenyuan'"  label="员工工号" prop="yuangonggonghao">
					<el-input v-model="ruleForm.yuangonggonghao" :readonly="ro.yuangonggonghao" placeholder="员工工号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='gongzuorenyuan'"  label="员工姓名" prop="yuangongxingming">
					<el-input v-model="ruleForm.yuangongxingming" :readonly="ro.yuangongxingming" placeholder="员工姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='gongzuorenyuan'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in gongzuorenyuanxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='gongzuorenyuan'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="gongzuorenyuantouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='gongzuorenyuan'"  label="联系方式" prop="lianxifangshi">
					<el-input v-model="ruleForm.lianxifangshi" :readonly="ro.lianxifangshi" placeholder="联系方式" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","padding":"0 0 0 96px","margin":"20px 0 0","justifyContent":"flex-start","display":"flex"}'>
					<el-button class="btn3" type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-queren15"></span>
						提交
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
	import { 
		isMobile,
	} from "@/utils/validate";

	export default {
		data() {
			return {
				ruleForm: {},
				ro: {},
				flag: '',
				usersFlag: false,
				yonghuxingbieOptions: [],
				gongzuorenyuanxingbieOptions: [],
			};
		},
		mounted() {
			var table = this.$storage.get("sessionTable");
			this.flag = table;
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(table == 'yonghu') {
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
					if(table == 'gongzuorenyuan') {
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

					this.ruleForm = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.yonghuxingbieOptions = "男,女".split(',')
			this.gongzuorenyuanxingbieOptions = "男,女".split(',')
		},
		methods: {
			yonghutouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			gongzuorenyuantouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			usersimageUploadChange(fileUrls) {
				this.ruleForm.image = fileUrls;
			},
			onUpdateHandler() {
				if((!this.ruleForm.xuehao)&& 'yonghu'==this.flag){
					this.$message.error('学号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'yonghu'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.xingming)&& 'yonghu'==this.flag){
					this.$message.error('姓名不能为空');
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if((!this.ruleForm.shoujihaoma)&& 'yonghu'==this.flag){
					this.$message.error('手机号码不能为空');
					return
				}
				if('yonghu' ==this.flag && this.ruleForm.shoujihaoma&&(!isMobile(this.ruleForm.shoujihaoma))){
					this.$message.error(`手机号码应输入手机格式`);
					return
				}
				if((!this.ruleForm.yuangonggonghao)&& 'gongzuorenyuan'==this.flag){
					this.$message.error('员工工号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'gongzuorenyuan'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.yuangongxingming)&& 'gongzuorenyuan'==this.flag){
					this.$message.error('员工姓名不能为空');
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('gongzuorenyuan' ==this.flag && this.ruleForm.lianxifangshi&&(!isMobile(this.ruleForm.lianxifangshi))){
					this.$message.error(`联系方式应输入手机格式`);
					return
				}
				if('users'==this.flag && this.ruleForm.username.trim().length<1) {
					this.$message.error(`用户名不能为空`);
					return	
				}
				if(this.flag=='users'){
					this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
				}
				this.$http({
					url: `${this.$storage.get("sessionTable")}/update`,
					method: "post",
					data: this.ruleForm
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(this.flag=='users'){
							this.$storage.set('headportrait',this.ruleForm.image)
						}else {
							if(this.flag == 'yonghu') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
							if(this.flag == 'gongzuorenyuan') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
						}
						this.$message({
							message: "修改信息成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								window.location.reload();
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
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
				padding: 0 10px 0 0;
				color: #666;
				white-space: nowrap;
				font-weight: 500;
				width: 180px;
				font-size: 16px;
				line-height: 40px;
				text-align: right;
			}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 12px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: auto;
				font-size: 16px;
				border-color: #bcbcbc;
				border-width: 1px;
				border-style: solid;
				min-width: 35%;
				height: 40px;
			}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: 100%;
				font-size: 16px;
				border-color: #bcbcbc;
				border-width: 1px;
				border-style: solid;
				height: 40px;
			}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: 100%;
				font-size: 16px;
				border-color: #bcbcbc;
				border-width: 1px;
				border-style: solid;
				height: 40px;
			}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #bcbcbc;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				width: 80px;
				font-size: 26px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
				border: 1px solid #bcbcbc;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				width: 80px;
				font-size: 26px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
				padding: 12px;
				color: #666;
				font-size: 16px;
				border-color: #bcbcbc;
				min-height: 120px;
				border-radius: 0px;
				box-shadow: none;
				outline: none;
				width: auto;
				border-width: 1px;
				border-style: solid;
				min-width: 80%;
				height: auto;
			}
	
	.add-update-preview .btn3 {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 4px;
				outline: none;
				color: #fff;
				background: #76bd9e;
				width: auto;
				font-size: 16px;
				height: 40px;
				.iconfont {
						margin: 0 2px;
						color: #fff;
						display: none;
						font-size: 16px;
						height: 40px;
					}
	}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
