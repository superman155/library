<template>
	<div class="forum-preview">
		<div class="forum-title">
			<div>互动论坛</div>
		</div>
		<el-form :inline="true" :model="formSearch" class="list-form-pv">
			<el-form-item class="search-item">
				<el-input v-model="title" placeholder="标题"></el-input>
			</el-form-item>
			<div class="search-btn-item">
				<el-button class="searchBtn" type="primary" @click="getForumList(1)">
					<span class="icon iconfont icon-fangdajing07"></span>
					查询
				</el-button>
				<el-button class="pubBtn" type="primary" @click="toForumAdd">
					<span class="icon iconfont icon-tianjia13"></span>
					发布帖子
				</el-button>
			</div>
		</el-form>
		<div class="z-box">
			<div class="section-content" v-for="item in forumList" :key="item.id" @click.stop="toForumDetail(item)">
				<img class="item-img" :src="$config.baseUrl + item.cover.split(',')[0]" @click.stop="imgPreView($config.baseUrl + item.cover.split(',')[0])" />
				<div class="item-title">{{item.title}}</div>
				<div class="item-user">发布人：{{item.isanon==1&&item.userid!=userid?'匿名':item.username}}</div>
				<div class="item-time">{{item.addtime}}</div>
			</div>
		</div>
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
			:page-sizes="pageSizes"
			prev-text="<"
			next-text=">"
			:hide-on-single-page="true"
			:layout='["total","prev","pager","next","sizes","jumper"].join()'
			:total="total"
			@current-change="curChange"
			@size-change="sizeChange"
			@prev-click="prevClick"
			@next-click="nextClick"
			></el-pagination>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				formSearch: {},
				title: '',
				layouts: '',
				forumList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				previewImg: '',
				previewVisible: false,
				userid: Number(localStorage.getItem('frontUserid')),
				categoryIndex: 0,
				categoryList: [],
			}
		},
		created() {
			this.getForumList(1);
		},
		watch:{
			$route(newValue){
				this.getForumList(1);
			}
		},
		//方法集合
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			getForumList(page) {
				let params = {
					page,
					limit: this.pageSize,
					isdone: '开放',
					sort: 'istop,toptime',
					order: 'desc,desc',
					delflag: 0,
				};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				this.$http.get('forum/flist', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.forumList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getForumList(page);
			},
			prevClick(page) {
				this.getForumList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getForumList(1);
			},
			nextClick(page) {
				this.getForumList(page);
			},
			toForumAdd() {
				this.$router.push('/index/forumAdd');
			},
			toForumDetail(item) {
				this.$router.push({path: '/index/forumDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.forum-preview {
				padding: 0 10%;
				margin: 0px auto;
				align-content: flex-start;
				background: none;
				display: flex;
				width: 100%;
				font-size: 16px;
				align-items: flex-start;
				position: relative;
				flex-wrap: wrap;
				.forum-title {
						margin: 20px auto;
						width: 100%;
						div {
								padding: 0;
								color: #333;
								background: none;
								font-weight: 500;
								display: block;
								width: 100%;
								font-size: 30px;
								border-color: #000;
								border-width: 4px;
								line-height: 60px;
								border-style: double;
								text-align: center;
							}
		}
		.list-form-pv {
						padding: 10px;
						margin: 10px 0;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								/deep/ .el-form-item__content {
										display: flex;
										align-items: center;
									}
				.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid #ddd;
										border-radius: 0px;
										padding: 0 10px;
										margin: 0;
										outline: none;
										color: #666;
										width: 500px;
										font-size: 16px;
										line-height: 42px;
										height: 42px;
									}
			}
			.search-btn-item {
								display: flex;
								.searchBtn {
										cursor: pointer;
										border: 0;
										border-radius: 0px;
										padding: 0px 15px;
										margin: 0 10px 0 0;
										outline: none;
										background: #054acc;
										width: auto;
										font-size: 16px;
										line-height: 42px;
										height: 42px;
										.icon {
												margin: 0 5px 0 0;
												color: #fff;
											}
				}
				
				.searchBtn:hover {
										opacity: 0.8;
									}
				
				.pubBtn {
										cursor: pointer;
										border: 0;
										border-radius: 0px;
										padding: 0px 15px;
										margin: 0 10px 0 0;
										outline: none;
										color: #fff;
										background: #05ccb1;
										width: auto;
										font-size: 16px;
										line-height: 42px;
										height: 42px;
										.icon {
												margin: 0 5px 0 0;
												color: #fff;
												font-size: 16px;
											}
				}
				
				.pubBtn:hover {
										opacity: 0.8;
									}
			}
		}
		.z-box {
						padding: 0;
						margin: 20px 0 0 0;
						align-content: space-between;
						flex: 1;
						display: flex;
						width: calc(100% - 350px);
						flex-wrap: wrap;
						order: 4;
						.section-content {
								cursor: pointer;
								border-radius: 0px;
								padding: 10px;
								margin: 0 0px 20px;
								background: #fff;
								display: flex;
								width: calc(100% - 0px);
								border-color: #eee;
								border-width: 1px;
								align-items: center;
								border-style: solid;
								.item-img {
										border-radius: 40px;
										margin: 0 10px 0 0;
										width: 40px;
										transition: all .4s;
										height: 40px;
									}
				.item-title {
										color: #666;
										flex: 1;
										font-size: 16px;
									}
				.item-user {
										margin: 0 20px 0 0;
										color: #999;
										font-size: 15px;
									}
				.item-time {
										color: #999;
										font-size: 15px;
									}
			}
			.section-content:hover {
								border: 1px solid #054acc30;
								.item-title {
										color: #054acc;
									}
				.item-user {
										color: #054acc;
									}
				.item-time {
										color: #054acc;
									}
			}
		}
	}
</style>
