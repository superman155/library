




























<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="200px"
			>
			<el-form-item class="add-item" label="编号" prop="refno">
				<el-input v-model="ruleForm.refno" 
					placeholder="编号" clearable :readonly="ro.refno"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="名称" prop="mingcheng">
				<el-input v-model="ruleForm.mingcheng" 
					placeholder="名称" clearable :readonly="ro.mingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="藏书类型" prop="zangshuleixing">
				<el-input v-model="ruleForm.zangshuleixing" 
					placeholder="藏书类型" clearable :readonly="ro.zangshuleixing"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="楼层" prop="louceng">
				<el-input v-model="ruleForm.louceng" 
					placeholder="楼层" clearable :readonly="ro.louceng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="座位号" prop="seatnum">
				<el-input v-model="ruleForm.seatnum" 
					placeholder="座位号" clearable :readonly="ro.seatnum"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="签到时间" prop="qiandaoshijian">
				<el-date-picker
					:disabled="ro.qiandaoshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.qiandaoshijian" 
					type="datetime"
					:picker-options="qiandaoshijianPickerOptions"
					placeholder="签到时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="学号" prop="xuehao">
				<el-input v-model="ruleForm.xuehao" 
					placeholder="学号" clearable :readonly="ro.xuehao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="姓名" prop="xingming">
				<el-input v-model="ruleForm.xingming" 
					placeholder="姓名" clearable :readonly="ro.xingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="人脸识别" v-if="type!='cross' || (type=='cross' && !ro.touxiang)" prop="touxiang">
				<img class="upload-img" v-if="ruleForm.touxiang" :src="ruleForm.touxiang?baseUrl + ruleForm.touxiang:''">
				<div class="faceBtn" @click="imgAddClick">人脸识别</div>
			</el-form-item>
			<el-form-item class="add-item" v-else label="人脸识别" prop="touxiang">
				<img v-if="ruleForm.touxiang.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.touxiang.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.touxiang.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="手机号码" prop="shoujihaoma">
				<el-input v-model="ruleForm.shoujihaoma" 
					placeholder="手机号码" clearable :readonly="ro.shoujihaoma"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="签到备注" prop="qiandaobeizhu">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.qiandaobeizhu"
					placeholder="签到备注"
					v-model="ruleForm.qiandaobeizhu">
					</el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn" v-if="!faceMatchFlag" type="primary" @click="onSubmit(null)">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">人脸校验</span>
				</el-button>
				<el-button class="submitBtn" v-if="faceMatchFlag" type="primary" @click="onSubmit(null)">
					<span class="icon iconfont icon-dagou"></span>
					<span class="text">提交信息</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu16"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
		<imgAdd ref="imgAdd" @imgChange="imgChange"></imgAdd>
	</div>
</template>

<script>
import imgAdd from "@/components/img";
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					refno : false,
					mingcheng : false,
					zangshuleixing : false,
					louceng : false,
					seatnum : false,
					qiandaoshijian : false,
					qiandaobeizhu : false,
					xuehao : false,
					xingming : false,
					touxiang : false,
					shoujihaoma : false,
					yuangonggonghao : false,
					sfsh : false,
					shhf : false,
				},
				type: '',
				userface : '',
				faceMatchFlag: false,
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					refno: '',
					mingcheng: '',
					zangshuleixing: '',
					louceng: '',
					seatnum: '',
					qiandaoshijian: '',
					qiandaobeizhu: '',
					xuehao: '',
					xingming: '',
					touxiang: '',
					shoujihaoma: '',
					yuangonggonghao: '',
				},

				// 只能选择今天和之后日期
				qiandaoshijianPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				rules: {
					refno: [
					],
					mingcheng: [
						{ required: true, message: '名称不能为空', trigger: 'blur' },
					],
					zangshuleixing: [
					],
					louceng: [
					],
					seatnum: [
					],
					qiandaoshijian: [
					],
					qiandaobeizhu: [
					],
					xuehao: [
					],
					xingming: [
					],
					touxiang: [
					],
					shoujihaoma: [
					],
					yuangonggonghao: [
					],
					sfsh: [
					],
					shhf: [
					],
				},
				centerType: false,
			};
		},
		computed: {
			sessionForm() {
				return JSON.parse(localStorage.getItem('sessionForm'))
			},



		},
		components: {
			imgAdd,
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.qiandaoshijian = this.getCurDateTime()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			imgAddClick(){
				this.$refs.imgAdd.onTake()
			},
			imgChange(e){
				this.ruleForm.touxiang = 'upload/' + e
			},
			// 下载
			download(file ){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='refno'){
							this.ruleForm.refno = obj[o];
							this.ro.refno = true;
							continue;
						}
						if(o=='mingcheng'){
							this.ruleForm.mingcheng = obj[o];
							this.ro.mingcheng = true;
							continue;
						}
						if(o=='zangshuleixing'){
							this.ruleForm.zangshuleixing = obj[o];
							this.ro.zangshuleixing = true;
							continue;
						}
						if(o=='louceng'){
							this.ruleForm.louceng = obj[o];
							this.ro.louceng = true;
							continue;
						}
						if(o=='seatnum'){
							this.ruleForm.seatnum = obj[o];
							this.ro.seatnum = true;
							continue;
						}
						if(o=='qiandaoshijian'){
							this.ruleForm.qiandaoshijian = obj[o];
							this.ro.qiandaoshijian = true;
							continue;
						}
						if(o=='qiandaobeizhu'){
							this.ruleForm.qiandaobeizhu = obj[o];
							this.ro.qiandaobeizhu = true;
							continue;
						}
						if(o=='xuehao'){
							this.ruleForm.xuehao = obj[o];
							this.ro.xuehao = true;
							continue;
						}
						if(o=='xingming'){
							this.ruleForm.xingming = obj[o];
							this.ro.xingming = true;
							continue;
						}
						if(o=='touxiang'){
							this.ruleForm.touxiang = obj[o]?obj[o].split(",")[0]:'';
							this.ro.touxiang = true;
							continue;
						}
						if(o=='shoujihaoma'){
							this.ruleForm.shoujihaoma = obj[o];
							this.ro.shoujihaoma = true;
							continue;
						}
						if(o=='yuangonggonghao'){
							this.ruleForm.yuangonggonghao = obj[o];
							this.ro.yuangonggonghao = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						this.userface = res.data.data.touxiang||res.data.data.headportrait;
						if((json.xuehao!=''&&json.xuehao) || json.xuehao==0){
							this.ruleForm.xuehao = json.xuehao;
							this.ro.xuehao = true;
						}
						if((json.xingming!=''&&json.xingming) || json.xingming==0){
							this.ruleForm.xingming = json.xingming;
							this.ro.xingming = true;
						}
						if((json.shoujihaoma!=''&&json.shoujihaoma) || json.shoujihaoma==0){
							this.ruleForm.shoujihaoma = json.shoujihaoma;
							this.ro.shoujihaoma = true;
						}
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit(null)
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			async info() {
				await this.$http.get(`qiandaodengji/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit(subMitType=null) {
				if(!this.faceMatchFlag) {
					//人脸校验
					if(!this.ruleForm.touxiang) {
						this.$message({
						  message: "请上传人脸图片",
						  type: 'error',
						  duration: 1500
						});
						return;
					}
					if(this.ruleForm.touxiang!=null) {
						this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
					}
					let params = {
						face1:this.userface.replace("upload/",""),
						face2:this.ruleForm.touxiang.replace("upload/",""),
					}
					this.$http.get('matchFace', {params: params}).then(res => {
						if (res.data && res.data.code == 0) {
							if(res.data.score>60) {
								this.faceMatchFlag = true;
								this.$message({
									message: "匹配成功",
									type: "success",
									duration: 1500,
								});
							} else {
								this.faceMatchFlag = false;
								this.$alert('匹配失败，重新上传', '提示', {
									confirmButtonText: '确定',
									showClose: false,
									type: 'warning'
								}).then(() => {
								})
								return;
							}
						} else {
							this.faceMatchFlag = false;
							this.$alert('匹配失败，重新上传', '提示', {
								confirmButtonText: '确定',
								showClose: false,
								type: 'warning'
							}).then(() => {
							})
							return;
						}
					});
					return;
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(!this.ruleForm.id) {
							delete this.ruleForm.userid
						}
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									var notUpdateColumn = 'qiandaozhuangtai'.split(',')
									if(changeInList(statusColumnName,notUpdateColumn)) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = localStorage.getItem('crossTable');
										await this.$http.post(table+'/update', obj).then(res => {});
									}
									function changeInList(name,arr){
										for(let x in arr) {
											if(arr[x] == name) {
												return false
											}
										}
										return true
									}
								}
							}
						}

						let changeType = true
						let errMsg = ''
						let hasBackType = false
						await this.$http.post(`qiandaodengji/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								await this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			touxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 10% 40px;
		margin: 10px auto;
		background: none;
		width: 100%;
		position: relative;
		.add-update-form {
			border: 1px solid #eee;
			border-radius: 10px;
			padding: 40px 20% 20px 10%;
			background: #fff;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 0;
				margin: 0 0 24px;
				background: none;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #333;
					white-space: nowrap;
					font-weight: 500;
					width: 200px;
					font-size: 16px;
					line-height: 40px;
					text-align: right;
				}
				/deep/ .el-form-item__content {
					margin-left: 200px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #eee;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #eee;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					width: 100%;
					font-size: 16px;
					height: 40px;
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
					border: 1px solid #eee;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: #eee;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #eee;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: #eee;
					width: 100%;
					font-size: 16px;
					height: 40px;
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
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
				}
				/deep/ .el-upload__tip {
					color: #666;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #eee;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: #123;
					font-size: 16px;
				}
				/deep/ textarea::placeholder {
					color: #123;
					font-size: 16px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.editor /deep/.ql-toolbar {
					border: 1px solid #eee;
					background: none;
					border-width: 1px 1px 0;
				}
				.editor /deep/.ql-container {
					border: 1px solid #eee;
					background: none;
					min-height: 180px;
				}
				.editor /deep/.ql-container .ql-blank::before {
					color: #999;
				}
				.faceBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #054acc;
					width: auto;
					height: 34px;
				}
				.faceBtn:hover {
					opacity: 0.7;
				}
				.upload-img {
					object-fit: cover;
					width: 120px;
					height: 120px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #054acc;
					width: auto;
					height: 34px;
				}
				.viewBtn:hover {
					opacity: 0.7;
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #666;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #ddd;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
					opacity: 0.8;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px auto;
				width: 100%;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 20px;
					margin: 0 20px 0 0;
					outline: none;
					background: #054acc;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: rgba(255, 255, 255, 1);
					}
					.text {
						color: rgba(255, 255, 255, 1);
						font-size: 16px;
					}
				}
				.submitBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 1px solid #ff000020;
					cursor: pointer;
					padding: 0 15px 0 10px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 4px;
					outline: none;
					background: #ff000010;
					width: auto;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #cc0000;
						font-size: 18px;
					}
					.text {
						color: #cc0000;
						font-size: 16px;
					}
				}
				.closeBtn:hover {
					opacity: 0.7;
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
