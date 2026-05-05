






















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
				<el-form-item class="input" v-if="type!='info'"  label="论坛id" prop="forumid" >
					<el-input v-model="ruleForm.forumid" placeholder="论坛id" clearable  :readonly="ro.forumid"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="论坛id" prop="forumid" >
					<el-input v-model="ruleForm.forumid" placeholder="论坛id" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="帖子标题" prop="title" >
					<el-input v-model="ruleForm.title" placeholder="帖子标题" clearable  :readonly="ro.title"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="帖子标题" prop="title" >
					<el-input v-model="ruleForm.title" placeholder="帖子标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="举报用户名" prop="username" >
					<el-input v-model="ruleForm.username" placeholder="举报用户名" clearable  :readonly="ro.username"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="举报用户名" prop="username" >
					<el-input v-model="ruleForm.username" placeholder="举报用户名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="被举报用户id" prop="reporteduserid" >
					<el-input v-model="ruleForm.reporteduserid" placeholder="被举报用户id" clearable  :readonly="ro.reporteduserid"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="被举报用户id" prop="reporteduserid" >
					<el-input v-model="ruleForm.reporteduserid" placeholder="被举报用户id" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="被举报用户名" prop="reportedusername" >
					<el-input v-model="ruleForm.reportedusername" placeholder="被举报用户名" clearable  :readonly="ro.reportedusername"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="被举报用户名" prop="reportedusername" >
					<el-input v-model="ruleForm.reportedusername" placeholder="被举报用户名" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.picture" label="图片补充" prop="picture" >
					<file-upload
						tip="点击上传图片补充"
						action="file/upload"
						:limit="3"
						:disabled="ro.picture"
						:multiple="true"
						:fileUrls="ruleForm.picture?ruleForm.picture:''"
						@change="pictureUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.picture" label="图片补充" prop="picture" >
					<img v-if="ruleForm.picture.substring(0,4)=='http'&&ruleForm.picture.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.picture" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.picture.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.picture.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.picture.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="状态" prop="status" >
					<el-select :disabled="ro.status" v-model="ruleForm.status" placeholder="请选择状态"  filterable>
						<el-option
							v-for="(item,index) in statusOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="状态" prop="status" >
					<el-input v-model="ruleForm.status"
						placeholder="状态" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="举报类型" prop="reporttype" >
					<el-select :disabled="ro.reporttype" v-model="ruleForm.reporttype" placeholder="请选择举报类型"  filterable>
						<el-option
							v-for="(item,index) in reporttypeOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="举报类型" prop="reporttype" >
					<el-input v-model="ruleForm.reporttype"
						placeholder="举报类型" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="举报原因" prop="reason" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="举报原因"
					v-model="ruleForm.reason" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.reason" label="举报原因" prop="reason"  class="textBox">
				<span class="text">{{ruleForm.reason}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="处理建议" prop="handleadvise" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="处理建议"
					v-model="ruleForm.handleadvise" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.handleadvise" label="处理建议" prop="handleadvise"  class="textBox">
				<span class="text">{{ruleForm.handleadvise}}</span>
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
					forumid : false,
					title : false,
					userid : false,
					username : false,
					reporteduserid : false,
					reportedusername : false,
					reason : false,
					picture : false,
					handleadvise : false,
					status : false,
					reporttype : false,
				},
			
				ruleForm: {
					forumid: '',
					title: '',
					userid: '',
					username: '',
					reporteduserid: '',
					reportedusername: '',
					reason: '',
					picture: '',
					handleadvise: '',
					status: '处理中',
					reporttype: '主题帖举报',
				},
				statusOptions: [],
				reporttypeOptions: [],

				rules: {
					forumid: [
					],
					title: [
					],
					userid: [
						{ required: true, message: '举报用户id不能为空', trigger: 'blur' },
					],
					username: [
					],
					reporteduserid: [
						{ required: true, message: '被举报用户id不能为空', trigger: 'blur' },
					],
					reportedusername: [
					],
					reason: [
						{ required: true, message: '举报原因不能为空', trigger: 'blur' },
					],
					picture: [
					],
					handleadvise: [
					],
					status: [
					],
					reporttype: [
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
						if(o=='forumid'){
							this.ruleForm.forumid = obj[o];
							this.ro.forumid = true;
							continue;
						}
						if(o=='title'){
							this.ruleForm.title = obj[o];
							this.ro.title = true;
							continue;
						}
						if(o=='userid'){
							this.ruleForm.userid = obj[o];
							this.ro.userid = true;
							continue;
						}
						if(o=='username'){
							this.ruleForm.username = obj[o];
							this.ro.username = true;
							continue;
						}
						if(o=='reporteduserid'){
							this.ruleForm.reporteduserid = obj[o];
							this.ro.reporteduserid = true;
							continue;
						}
						if(o=='reportedusername'){
							this.ruleForm.reportedusername = obj[o];
							this.ro.reportedusername = true;
							continue;
						}
						if(o=='reason'){
							this.ruleForm.reason = obj[o];
							this.ro.reason = true;
							continue;
						}
						if(o=='picture'){
							this.ruleForm.picture = obj[o];
							this.ro.picture = true;
							continue;
						}
						if(o=='handleadvise'){
							this.ruleForm.handleadvise = obj[o];
							this.ro.handleadvise = true;
							continue;
						}
						if(o=='status'){
							this.ruleForm.status = obj[o];
							this.ro.status = true;
							continue;
						}
						if(o=='reporttype'){
							this.ruleForm.reporttype = obj[o];
							this.ro.reporttype = true;
							continue;
						}
					}
				}

				this.statusOptions = "处理中,已处理".split(',')
				this.reporttypeOptions = "主题帖举报".split(',')
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `forumreport/info/${id}`,
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
							if(this.ruleForm.picture!=null) {
								this.ruleForm.picture = this.ruleForm.picture.replace(new RegExp(this.$base.url,"g"),"");
							}
							var objcross = this.$storage.getObj('crossObj');
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
								}
							}
							await this.$http({
								url: `forumreport/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.forumreportCrossAddOrUpdateFlag = false;
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
				this.parent.forumreportCrossAddOrUpdateFlag = false;
			},
			pictureUploadChange(fileUrls) {
				this.ruleForm.picture = fileUrls;
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
