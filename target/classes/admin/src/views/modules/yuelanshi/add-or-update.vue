








































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
				<el-form-item class="input" v-if="type!='info'"  label="编号" prop="refno" >
					<el-input v-model="ruleForm.refno" placeholder="编号" clearable  :readonly="ro.refno"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="编号" prop="refno" >
					<el-input v-model="ruleForm.refno" placeholder="编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="名称" prop="mingcheng" >
					<el-input v-model="ruleForm.mingcheng" placeholder="名称" clearable  :readonly="ro.mingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="名称" prop="mingcheng" >
					<el-input v-model="ruleForm.mingcheng" placeholder="名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="图片" prop="tupian" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:disabled="ro.tupian"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="图片" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="藏书类型" prop="zangshuleixing" >
					<el-select :disabled="ro.zangshuleixing" v-model="ruleForm.zangshuleixing" placeholder="请选择藏书类型"  filterable>
						<el-option
							v-for="(item,index) in zangshuleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="藏书类型" prop="zangshuleixing" >
					<el-input v-model="ruleForm.zangshuleixing"
						placeholder="藏书类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="楼层" prop="louceng" >
					<el-input v-model="ruleForm.louceng" placeholder="楼层" clearable  :readonly="ro.louceng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="楼层" prop="louceng" >
					<el-input v-model="ruleForm.louceng" placeholder="楼层" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="分区" prop="fenqu" >
					<el-select :disabled="ro.fenqu" v-model="ruleForm.fenqu" placeholder="请选择分区"  filterable>
						<el-option
							v-for="(item,index) in fenquOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="分区" prop="fenqu" >
					<el-input v-model="ruleForm.fenqu"
						placeholder="分区" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="列数" prop="columns" >
					<el-input v-model.number="ruleForm.columns" placeholder="列数" clearable  :readonly="ro.columns"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="列数" prop="columns" >
					<el-input v-model="ruleForm.columns" placeholder="列数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="行数" prop="rows" >
					<el-input v-model.number="ruleForm.rows" placeholder="行数" clearable  :readonly="ro.rows"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="行数" prop="rows" >
					<el-input v-model="ruleForm.rows" placeholder="行数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="总数" prop="totals" >
					<el-input v-model="totals" placeholder="总数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.totals" label="总数" prop="totals" >
					<el-input v-model="ruleForm.totals" placeholder="总数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="开放时间" prop="opentime" >
					<el-input v-model="ruleForm.opentime" placeholder="开放时间" clearable  :readonly="ro.opentime"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="开放时间" prop="opentime" >
					<el-input v-model="ruleForm.opentime" placeholder="开放时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="阅览室地址" prop="yuelanshidizhi" >
					<el-input v-model="ruleForm.yuelanshidizhi" placeholder="阅览室地址" clearable  :readonly="ro.yuelanshidizhi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="阅览室地址" prop="yuelanshidizhi" >
					<el-input v-model="ruleForm.yuelanshidizhi" placeholder="阅览室地址" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="员工工号" prop="yuangonggonghao" style="display:none">
					<el-input v-model="ruleForm.yuangonggonghao" placeholder="员工工号" clearable  :readonly="ro.yuangonggonghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="员工工号" prop="yuangonggonghao" style="display:none">
					<el-input v-model="ruleForm.yuangonggonghao" placeholder="员工工号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工作员" prop="yuangongxingming" >
					<el-input v-model="ruleForm.yuangongxingming" placeholder="工作员" clearable  :readonly="ro.yuangongxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="工作员" prop="yuangongxingming" >
					<el-input v-model="ruleForm.yuangongxingming" placeholder="工作员" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'" class="editorBox" label="阅览室详情" prop="yuelanshixiangqing" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.yuelanshixiangqing" 
					class="editor"
					myQuillEditor="yuelanshixiangqing"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.yuelanshixiangqing" label="阅览室详情" prop="yuelanshixiangqing"  class="textBox">
				<span class="text ql-snow ql-editor" v-html="ruleForm.yuelanshixiangqing"></span>
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
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					refno : false,
					mingcheng : false,
					tupian : false,
					zangshuleixing : false,
					louceng : false,
					fenqu : false,
					columns : false,
					rows : false,
					totals : false,
					opentime : false,
					yuelanshidizhi : false,
					yuelanshixiangqing : false,
					yuangonggonghao : false,
					yuangongxingming : false,
					thumbsupnum : false,
					crazilynum : false,
					clicktime : false,
					clicknum : false,
					discussnum : false,
					storeupnum : false,
				},
			
				ruleForm: {
					refno: '',
					mingcheng: '',
					tupian: '',
					zangshuleixing: '',
					louceng: '',
					fenqu: '',
					columns: '',
					rows: '',
					totals: '',
					opentime: '08:00-18:00',
					yuelanshidizhi: '',
					yuelanshixiangqing: '',
					yuangonggonghao: '',
					yuangongxingming: '',
					thumbsupnum: Number('0'),
					crazilynum: Number('0'),
					clicktime: '',
					clicknum: Number('0'),
					discussnum: Number('0'),
					storeupnum: Number('0'),
				},
				zangshuleixingOptions: [],
				fenquOptions: [],

				rules: {
					refno: [
					],
					mingcheng: [
						{ required: true, message: '名称不能为空', trigger: 'blur' },
					],
					tupian: [
					],
					zangshuleixing: [
					],
					louceng: [
					],
					fenqu: [
					],
					columns: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					rows: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					totals: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					opentime: [
					],
					yuelanshidizhi: [
					],
					yuelanshixiangqing: [
						{ required: true, message: '阅览室详情不能为空', trigger: 'blur' },
					],
					yuangonggonghao: [
					],
					yuangongxingming: [
					],
					thumbsupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					clicktime: [
					],
					clicknum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
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


			totals:{
				get: function () {
					return 1*this.ruleForm.columns*this.ruleForm.rows
				}
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
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o];
							this.ro.tupian = true;
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
						if(o=='fenqu'){
							this.ruleForm.fenqu = obj[o];
							this.ro.fenqu = true;
							continue;
						}
						if(o=='columns'){
							this.ruleForm.columns = obj[o];
							this.ro.columns = true;
							continue;
						}
						if(o=='rows'){
							this.ruleForm.rows = obj[o];
							this.ro.rows = true;
							continue;
						}
						if(o=='totals'){
							this.ruleForm.totals = obj[o];
							this.ro.totals = true;
							continue;
						}
						if(o=='opentime'){
							this.ruleForm.opentime = obj[o];
							this.ro.opentime = true;
							continue;
						}
						if(o=='yuelanshidizhi'){
							this.ruleForm.yuelanshidizhi = obj[o];
							this.ro.yuelanshidizhi = true;
							continue;
						}
						if(o=='yuelanshixiangqing'){
							this.ruleForm.yuelanshixiangqing = obj[o];
							this.ro.yuelanshixiangqing = true;
							continue;
						}
						if(o=='yuangonggonghao'){
							this.ruleForm.yuangonggonghao = obj[o];
							this.ro.yuangonggonghao = true;
							continue;
						}
						if(o=='yuangongxingming'){
							this.ruleForm.yuangongxingming = obj[o];
							this.ro.yuangongxingming = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
					this.ruleForm.opentime = '08:00-18:00'; 				}

				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.yuangonggonghao!=''&&json.yuangonggonghao) || json.yuangonggonghao==0) && this.sessionTable!="users"){
							this.ruleForm.yuangonggonghao = json.yuangonggonghao
							this.ro.yuangonggonghao = true;
						}
						if(((json.yuangongxingming!=''&&json.yuangongxingming) || json.yuangongxingming==0) && this.sessionTable!="users"){
							this.ruleForm.yuangongxingming = json.yuangongxingming
							this.ro.yuangongxingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/zangshuleixing/zangshuleixing`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.zangshuleixingOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.fenquOptions = "A区,B区,C区,D区".split(',')
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `yuelanshi/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.yuelanshixiangqing = this.ruleForm.yuelanshixiangqing.replace(reg,'../../../book0c04pxbv/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							this.ruleForm.totals = this.totals
							if(this.ruleForm.tupian!=null) {
								this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
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
								url: `yuelanshi/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.yuelanshiCrossAddOrUpdateFlag = false;
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
				this.parent.yuelanshiCrossAddOrUpdateFlag = false;
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
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
