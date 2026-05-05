<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'>'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType " class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-fanhui21"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<div class="list-btn-box">
					<el-button class="list-search-btn" v-if=" false " type="primary" @click="getList(1, curFenlei)">
						<span class="icon iconfont icon-fangdajing07"></span>
						搜索
					</el-button>
					<el-button class="list-add-btn" v-if="btnAuth('forumreport','新增')" type="primary" @click="add('/index/forumreportAdd')">
						<span class="icon iconfont icon-tianjia14"></span>
						新增
					</el-button>
  
				</div>
			</el-form>
			<div class="list">
				<el-table class="tables" :stripe='false'
					:style='{"width":"100%","padding":"0","borderColor":"#eee","borderStyle":"solid","borderWidth":"1px 0 0 1px","background":"#fff"}' 
					:border='true' :data="dataList">
					<el-table-column :resizable='true' :sortable='false' prop="title" label="标题"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="reason" label="举报原因"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="picture" label="图片补充">
						<template slot-scope="scope">
							<img v-if="scope.row.picture" :src="baseUrl + scope.row.picture.split(',')[0]" @click.stop="imgPreView(baseUrl + (scope.row.picture?scope.row.picture.split(',')[0]:''))" style="width: 150px;height: 150px">
							<span v-else>无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="reporttype" label="举报类型"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="handleadvise" label="处理建议"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="status" label="状态"></el-table-column>
					<el-table-column width="100" label="操作">
						<template slot-scope="scope">
							<el-button class="view" type="success" size="mini"
								@click="toDetail(scope.row)">
								查看
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	import axios from 'axios';
	export default {
		//数据集合
		data() {
			return {
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '论坛举报'
					}
				],
				formSearch: {
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 15,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			if(this.centerType) {
			}
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		computed: {
			role(){
				return localStorage.getItem('frontRole');
			},
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`forumreport/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(async res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/forumreportDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},

			chapterClick (row){
				let params = {
					refid: row.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/chapterforumreport', query: params});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 10%;
		margin: 0px auto;
		align-content: flex-start;
		background: none;
		display: flex;
		width: 100%;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.list-form-pv {
			padding: 0;
			margin: 10px auto;
			background: none;
			display: flex;
			width: 100%;
			border-color: #ddd;
			border-width: 0px;
			justify-content: center;
			align-items: center;
			border-style: solid;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				margin: 0 5px 0 0;
				display: flex;
				align-items: center;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0;
					color: #333;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: 100%;
				}
				.datetimerange {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 3px 0px;
					outline: none;
					background: #fff;
					display: flex;
					width: auto;
					justify-content: center;
					align-items: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-btn-box {
				display: flex;
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 10px;
				margin: 0 10px 0 0;
				outline: none;
				color: #fff;
				background: #054acc;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				.icon {
					margin: 0 3px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 10px;
				margin: 0 10px 0 0;
				outline: none;
				color: #f28d00;
				background: #f28d0030;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				.icon {
					margin: 0 3px 0 0;
					color: #f28d00;
					font-size: 14px;
				}
			}
		}
		.list {
			margin: 20px 0 0;
			background: none;
			flex: 1;
			width: calc(100% - 350px);
			order: 4;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.el-table /deep/ .el-table__header-wrapper thead {
				color: #333;
				font-weight: 500;
				width: 100%;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #fff;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}
			
			
			.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #666;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			
			.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 12px 0;
				color: #333;
				background: rgba(64, 158, 255, .1);
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #666;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
		}
	}
</style>
