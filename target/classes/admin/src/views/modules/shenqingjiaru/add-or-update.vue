






















<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="标题" prop="biaoti" >
					<el-input v-model="ruleForm.biaoti" placeholder="标题" clearable  :readonly="ro.biaoti"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="标题" prop="biaoti" >
					<el-input v-model="ruleForm.biaoti" placeholder="标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.fengmian" label="封面" prop="fengmian" >
					<file-upload
						tip="点击上传封面"
						action="file/upload"
						:limit="3"
						:disabled="ro.fengmian"
						:multiple="true"
						:fileUrls="ruleForm.fengmian?ruleForm.fengmian:''"
						@change="fengmianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.fengmian" label="封面" prop="fengmian" >
					<img v-if="ruleForm.fengmian.substring(0,4)=='http'&&ruleForm.fengmian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="学习风格" prop="xuexifengge" >
					<el-input v-model="ruleForm.xuexifengge" placeholder="学习风格" clearable  :readonly="ro.xuexifengge"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="学习风格" prop="xuexifengge" >
					<el-input v-model="ruleForm.xuexifengge" placeholder="学习风格" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="监督机制" prop="jiandujizhi" >
					<el-input v-model="ruleForm.jiandujizhi" placeholder="监督机制" clearable  :readonly="ro.jiandujizhi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="监督机制" prop="jiandujizhi" >
					<el-input v-model="ruleForm.jiandujizhi" placeholder="监督机制" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="搭子类型" prop="dazileixing" >
					<el-input v-model="ruleForm.dazileixing" placeholder="搭子类型" clearable  :readonly="ro.dazileixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="搭子类型" prop="dazileixing" >
					<el-input v-model="ruleForm.dazileixing" placeholder="搭子类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="申请时间" prop="shenqingshijian" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.shenqingshijian" 
						type="date"
						:readonly="ro.shenqingshijian"
						:picker-options="shenqingshijianPickerOptions"
						placeholder="申请时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.shenqingshijian" label="申请时间" prop="shenqingshijian" >
					<el-input v-model="ruleForm.shenqingshijian" placeholder="申请时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="学号" prop="xuehao" >
					<el-input v-model="ruleForm.xuehao" placeholder="学号" clearable  :readonly="ro.xuehao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="学号" prop="xuehao" >
					<el-input v-model="ruleForm.xuehao" placeholder="学号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="加入原因" prop="jiaruyuanyin" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="加入原因"
					v-model="ruleForm.jiaruyuanyin" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.jiaruyuanyin" label="加入原因" prop="jiaruyuanyin"  class="textBox">
				<span class="text">{{ruleForm.jiaruyuanyin}}</span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-queren15"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-guanbi2"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui13"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					biaoti : false,
					fengmian : false,
					xuexifengge : false,
					jiandujizhi : false,
					dazileixing : false,
					jiaruyuanyin : false,
					shenqingshijian : false,
					xuehao : false,
					xingming : false,
					crossuserid : false,
					crossrefid : false,
				},
			
				ruleForm: {
					biaoti: '',
					fengmian: '',
					xuexifengge: '',
					jiandujizhi: '',
					dazileixing: '',
					jiaruyuanyin: '',
					shenqingshijian: '',
					xuehao: '',
					xingming: '',
					crossuserid: '',
					crossrefid: '',
				},

				shenqingshijianPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				rules: {
					biaoti: [
						{ required: true, message: '标题不能为空', trigger: 'blur' },
					],
					fengmian: [
					],
					xuexifengge: [
					],
					jiandujizhi: [
					],
					dazileixing: [
					],
					jiaruyuanyin: [
						{ required: true, message: '加入原因不能为空', trigger: 'blur' },
					],
					shenqingshijian: [
					],
					xuehao: [
					],
					xingming: [
					],
					crossuserid: [
					],
					crossrefid: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {
			sessionForm() {
				return JSON.parse(this.$storage.getObj('userForm'))
			},
			sessionTable() {
				return this.$storage.get('sessionTable')
			},



		},
		components: {
		},
		created() {
			this.ruleForm.shenqingshijian = this.getCurDate()
		},
		methods: {
			imgPreView(url){
				this.$parent.imgPreView(url)
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type ) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='biaoti'){
							this.ruleForm.biaoti = obj[o];
							this.ro.biaoti = true;
							continue;
						}
						if(o=='fengmian'){
							this.ruleForm.fengmian = obj[o];
							this.ro.fengmian = true;
							continue;
						}
						if(o=='xuexifengge'){
							this.ruleForm.xuexifengge = obj[o];
							this.ro.xuexifengge = true;
							continue;
						}
						if(o=='jiandujizhi'){
							this.ruleForm.jiandujizhi = obj[o];
							this.ro.jiandujizhi = true;
							continue;
						}
						if(o=='dazileixing'){
							this.ruleForm.dazileixing = obj[o];
							this.ro.dazileixing = true;
							continue;
						}
						if(o=='jiaruyuanyin'){
							this.ruleForm.jiaruyuanyin = obj[o];
							this.ro.jiaruyuanyin = true;
							continue;
						}
						if(o=='shenqingshijian'){
							this.ruleForm.shenqingshijian = obj[o];
							this.ro.shenqingshijian = true;
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
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
							continue;
						}
					}
				}

				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.xuehao!=''&&json.xuehao) || json.xuehao==0) && this.sessionTable!="users"){
							this.ruleForm.xuehao = json.xuehao
							this.ro.xuehao = true;
						}
						if(((json.xingming!=''&&json.xingming) || json.xingming==0) && this.sessionTable!="users"){
							this.ruleForm.xingming = json.xingming
							this.ro.xingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `shenqingjiaru/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.ruleForm.fengmian!=null) {
								this.ruleForm.fengmian = this.ruleForm.fengmian.replace(new RegExp(this.$base.url,"g"),"");
							}
							var objcross = this.$storage.getObj('crossObj');
							//更新跨表属性
							var crossuserid;
							var crossrefid;
							var crossoptnum;
							var finishNum = 0;
							if(!this.ruleForm.id) {
								delete this.ruleForm.userid
							}
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
									else {
										crossuserid=this.$storage.get('userid');
										crossrefid=obj['id'];
										crossoptnum=this.$storage.get('statusColumnName');
										crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
									}
								}
								if(crossrefid && crossuserid) {
									this.ruleForm.crossuserid = crossuserid;
									this.ruleForm.crossrefid = crossrefid;
									let params = { 
										page: 1, 
										limit: 10, 
										crossuserid:this.ruleForm.crossuserid,
										crossrefid:this.ruleForm.crossrefid,
									} 
									await this.$http({ 
										url: "shenqingjiaru/page", 
										method: "get", 
										params: params 
									}).then(({ 
										data 
									}) => { 
										if (data && data.code === 0) {
											finishNum = data.data.total
										}
									})
								}
								if(finishNum>=crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								}
							}
							await this.$http({
								url: `shenqingjiaru/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.shenqingjiaruCrossAddOrUpdateFlag = false;
											this.parent.search();
										}
									});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.shenqingjiaruCrossAddOrUpdateFlag = false;
			},
			fengmianUploadChange(fileUrls) {
				this.ruleForm.fengmian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		border: 1px solid #00000050;
		border-radius: 10px;
		padding: 40px 30px;
		box-shadow: 0px 4px 3px #8558ff50;
		background: #ffffff;
		display: flex;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		margin: 0 0 20px 0;
		width: 100%;
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
	.add-update-preview /deep/ .el-form-item.editorBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 14px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__content {
		margin: 0;
		display: flex;
		align-items: center;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor {
		box-shadow: none;
		max-width: 100% !important;
		width: 100%;
		height: auto;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-toolbar {
		background: none;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container {
		background: none;
		min-height: 200px;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container .ql-blank::before {
	}
	
	.add-update-preview /deep/ .el-form-item.textBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	.add-update-preview /deep/.el-form-item.textBox span.text {
		padding: 0;
		color: #666;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
	}
	
	.add-update-preview .el-input {
		width: 100%;
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
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: auto;
		font-size: 16px;
		min-width: 35%;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
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
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: auto;
		font-size: 16px;
		min-width: 35%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: auto;
		min-width: 35%;
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
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: auto;
		min-width: 35%;
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
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px 0 30px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #dc3dfa;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 30px;
		margin: 0 20px 0 0;
		outline: none;
		color: #dc3dfa;
		background: #fcedff;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #dc3dfa;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 30px;
		margin: 0 20px 0 0;
		outline: none;
		color: #dc3dfa;
		background: #fcedff;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 1px solid #bcbcbc80;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 30px;
		margin: 0 20px 0 0;
		outline: none;
		color: #333;
		background: #f6f6f6;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
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
	
	.add-update-preview /deep/ .upload .upload-img {
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
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	.add-update-preview /deep/ .el-form-item.fileupload {
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__label {
	}
	
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__content {
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-upload-dragger {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-icon-upload {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text em {
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
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 12px;
		box-shadow: none;
		outline: none;
		color: #666;
		width: auto;
		font-size: 16px;
		min-width: 80%;
		height: auto;
	}
	.add-update-preview /deep/ .el-form-item.btn {
		padding: 0 0 0 96px;
		margin: 20px 0 0;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		.btn1 {
			border: 0;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #fff;
			background: #7b4ffb;
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
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #fff;
			background: #dc3dfa;
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
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
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
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #fff;
			background: #a4a4a4;
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
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #fff;
			background: #3b43f7;
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
		.btn5:hover {
			opacity: 0.8;
		}
	}
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		white-space: nowrap;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__content {
	}
</style>
