<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list26 index-pv1">
				<div class="list-body-top">
					<div v-if="newsList.length>0" class="list-item1" @click="toDetail('newsDetail', newsList[0])">
						<div class="img"><img :src="baseUrl + (newsList[0].picture?newsList[0].picture.split(',')[0]:'')" /></div>
						<div class="name">{{newsList[0].title}}</div>
					</div>
					<div class="list-body-right">
						<div v-if="newsList.length>1" class="list-item2" @click="toDetail('newsDetail', newsList[1])">
							<div class="img"><img :src="baseUrl + (newsList[1].picture?newsList[1].picture.split(',')[0]:'')" /></div>
							<div class="news_info">
								<div class="name">{{newsList[1].title}}</div>
								<div class="desc">{{newsList[1].introduction}}</div>
								<span class="time">{{newsList[1].addtime.split(' ')[0]}}</span>
							</div>
						</div>
						<div v-if="newsList.length>2" class="list-item2" @click="toDetail('newsDetail', newsList[2])">
							<div class="img"><img :src="baseUrl + (newsList[2].picture?newsList[2].picture.split(',')[0]:'')" /></div>
							<div class="news_info">
								<div class="name">{{newsList[2].title}}</div>
								<div class="desc">{{newsList[2].introduction}}</div>
								<span class="time">{{newsList[2].addtime.split(' ')[0]}}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body-bottom">
					<div v-for="(item,index) in newsList" v-if="index>2" :key="index" class="list-item3" @click="toDetail('newsDetail', item)">
						<div class="name">{{item.title}}</div>
						<span class="time">{{item.addtime.split(' ')[0]}}</span>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-jiantou25"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendyuelanshi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">阅览室推荐</span>
				<span class="recommend_subhead">{{'yuelanshi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="yuelanshiRecommend.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in yuelanshiRecommend" :key="index" @click="toDetail('yuelanshiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.mingcheng}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yuelanshi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 特价商品 -->
		<div id="animate_listyuelanshi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">阅览室展示</span>
				<span class="list_subhead">{{'yuelanshi'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="yuelanshiList.length" class="list list16 index-pv1">
				<div v-for="item,index in yuelanshiList" :key="index"  @click="toDetail('yuelanshiDetail', item)" class="list-item animation-box">
					<div class="img">
						<img v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
					</div>
					<div class="infoBox">
						<div class="info-left">
							<div class="name">{{item.mingcheng}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{item.addtime.split(' ')[0]}}</span>
							</div>
							<div class="publisher_item">
								<span class="icon iconfont icon-geren16"></span>
								<span class="label">发布人：</span>
								<span class="text">{{item.yuangonggonghao}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞数：</span>
								<span class="text">{{item.thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏量：</span>
								<span class="text">{{item.storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">点击量：</span>
								<span class="text">{{item.clicknum}}</span>
							</div>
						</div>
						<div class="desc ql-snow ql-editor" v-html="item.yuelanshixiangqing"></div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yuelanshi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-jiantou25"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				yuelanshiRecommend: [],

				yuelanshiList: [],




			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		computed: {
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			recommendIndexClick11(index, name) {
				this['recommendIndex11' + name] = index[this['recommendColumn11' + name]]
				this.getList()
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendyuelanshi',css:'animate__'},
					{id:'animate_listyuelanshi',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 7,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "yuelanshi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "yuelanshi/autoSort2";
				}
				data = {
					page: 1,
					limit: 4,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yuelanshiRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 4,
				}

				this.$http.get('yuelanshi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yuelanshiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id, storeupType: 1}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0 auto;
		align-content: flex-start;
		display: flex;
		width: 100%;
		align-items: flex-start;
		flex-wrap: wrap;
		.news {
			padding: 0 calc((100% - 1200px)/2);
			margin: 50px auto 30px;
			background: #fff;
			width: 100%;
			position: relative;
			order: 11;
			.news_title_box {
				margin: 0 auto 20px;
				background: none;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 0px;
				border-style: solid;
				flex-wrap: wrap;
				.news_title {
					border: 4px double #000;
					padding: 0;
					color: #333;
					background: none;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 60px;
					text-align: center;
					order: 2;
				}
				.news_subhead {
					margin: 0;
					color: #ddd;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 42px;
					line-height: 80px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list26 {
				padding: 0;
				margin: 0 auto;
				overflow: hidden;
				background: none;
				width: 100%;
				.list-body-top {
					margin: 20px 0 0;
					overflow: hidden;
					display: flex;
					width: 100%;
					justify-content: space-between;
					align-items: flex-start;
					height: auto;
					.list-item1 {
						cursor: pointer;
						width: calc(50% - 10px);
						height: auto;
						.img {
							overflow: hidden;
							width: 100%;
							height: 280px;
							img {
								transform: scale(1);
								object-fit: cover;
								width: 100%;
								transition: all 1s ease 0s;
								height: 100%;
							}
						}
						.name {
							border: 1px solid #eee;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							background: none;
							width: 100%;
							font-size: 16px;
							line-height: 60px;
							text-overflow: ellipsis;
							transition: all 0.3s ease 0s;
							text-align: center;
							height: 60px;
						}
					}
					.list-item1:hover {
						.img {
							img {
								transform: scale(1.05);
								transition: all 1s ease 0s;
							}
						}
						.name {
							color: #fff;
							background: #2d66d9;
							border-color: #2d66d9;
						}
					}
					.list-body-right {
						overflow: hidden;
						flex-direction: column;
						display: flex;
						width: calc(50% - 20px);
						justify-content: space-between;
						height: 340px;
						.list-item2 {
							border: 1px solid #eee;
							padding: 18px;
							background: none;
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0.3s ease 0s;
							height: 160px;
							.img {
								width: auto;
								height: auto;
								img {
									transform: scale(1);
									object-fit: cover;
									width: 130px;
									transition: all 1s ease 0s;
									height: 124px;
								}
							}
							.news_info {
								margin: 0 0 0 20px;
								.name {
									overflow: hidden;
									color: #333;
									white-space: nowrap;
									width: 100%;
									font-size: 16px;
									line-height: 30px;
									text-overflow: ellipsis;
									transition: all 0.3s ease 0s;
									height: 30px;
								}
								.desc {
									margin: 10px 0;
									overflow: hidden;
									color: #666;
									width: 100%;
									font-size: 14px;
									line-height: 24px;
									transition: all 0.3s ease 0s;
									height: 72px;
								}
								.time {
									color: #666;
									font-size: 14px;
									float: right;
									transition: all 0.3s ease 0s;
								}
							}
						}
						.list-item2:hover {
							cursor: pointer;
							background: #2d66d9;
							border-color: #2d66d9;
							.img {
								img {
									transform: scale(1.05);
								}
							}
							.news_info {
								.name {
									color: #fff;
								}
								.desc {
									color: #fff;
								}
								.time {
									color: #fff;
								}
							}
						}
					}
				}
				.list-body-bottom {
					margin: 30px 0 0 -10px;
					display: flex;
					width: calc(100% + 20px);
					flex-wrap: wrap;
					height: auto;
					.list-item3 {
						border: 1px solid #eee;
						padding: 0px 10px;
						margin: 0 10px 22px;
						background: none;
						display: flex;
						width: calc(50% - 20px);
						transition: all 0.3s ease 0s;
						height: 60px;
						.name {
							padding: 0 0 0 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							flex: 1;
							width: 100%;
							font-size: 15px;
							line-height: 60px;
							text-overflow: ellipsis;
							transition: all 0.3s ease 0s;
							height: 60px;
						}
						.time {
							color: #666;
							white-space: nowrap;
							font-size: 12px;
							line-height: 60px;
							transition: all 0.3s ease 0s;
						}
					}
					.list-item3:hover {
						cursor: pointer;
						background: #2d66d9;
						border-color: #2d66d9;
						.name {
							color: #fff;
						}
						.time {
							color: #fff;
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #04a44a30;
				cursor: pointer;
				padding: 0;
				margin: 0;
				display: block;
				line-height: 36px;
				right: calc((100% - 1200px)/2);
				border-radius: 36px;
				top: 25px;
				background: none;
				width: auto;
				position: absolute;
				text-align: center;
				.text {
					margin: 0;
					color: #666;
					font-size: 16px;
					order: 2;
				}
				.icon {
					color: #999;
					background: none;
					display: none;
					width: 23px;
					font-size: 16px;
					height: 7px;
				}
			}
		}
		.recommend {
			padding: 0 calc((100% - 1200px)/2);
			margin: 0 auto 0;
			background: #fff;
			width: 100%;
			border-color: #ddd;
			border-width: 0px 0 0 0;
			position: relative;
			border-style: solid;
			text-align: center;
			order: 0;
			.recommend_title_box {
				margin: 20px auto;
				background: none;
				display: flex;
				width: 100%;
				flex-wrap: wrap;
				.recommend_title {
					border: 4px double #000;
					padding: 0;
					color: #333;
					background: none;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 60px;
					text-align: center;
					order: 2;
				}
				.recommend_subhead {
					margin: 0;
					color: #ddd;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 42px;
					line-height: 80px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -4px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 20px 0px 0;
				background: #fff;
				display: flex;
				width: 100%;
				justify-content: space-between;
				height: auto;
				.tab_view {
					margin: 0 30px 0 0;
					flex-direction: column;
					display: flex;
					width: 250px;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 0 0px 10px;
						margin: 0 0 10px;
						width: 100%;
						border-color: #000;
						border-width: 0 0 1px 0;
						border-style: dotted;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							display: none;
							font-size: 16px;
							line-height: 44px;
						}
						img {
							margin: 0 5px 0 0;
							object-fit: cover;
							display: inline-block;
							width: 44px;
							height: 44px;
						}
						.text {
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-size: 16px;
							line-height: 44px;
							text-overflow: ellipsis;
						}
					}
					.more_btn {
						cursor: pointer;
						padding: 0 20px;
						margin: 0;
						display: none;
						width: 100%;
						border-color: #efefef;
						border-width: 0 0 1px 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
						.text {
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
					}
				}
				.list-body {
					flex: 1;
					background: none;
					display: flex;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						cursor: pointer;
						margin: 0 0 0 20px;
						width: calc(50% - 20px);
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 220px;
						}
						.name {
							padding: 0 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							width: 100%;
							font-size: 15px;
							line-height: 32px;
							text-overflow: ellipsis;
						}
						.price {
							padding: 0 10px;
							color: #f00;
							font-size: 16px;
							line-height: 1.5;
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #04a44a30;
				cursor: pointer;
				padding: 0;
				margin: 0;
				display: block;
				line-height: 36px;
				right: calc((100% - 1200px)/2);
				border-radius: 36px;
				top: 45px;
				background: none;
				width: auto;
				position: absolute;
				text-align: center;
				.text {
					margin: 0 5px 0 0;
					color: #666;
					font-size: 16px;
					order: 2;
				}
				.icon {
					margin: 0 10px 0 0;
					color: #fff;
					display: none;
					width: auto;
					font-size: 18px;
					height: 7px;
				}
			}
		}
		.lists {
			padding: 40px calc((100% - 1200px)/2) 60px;
			margin: 0 auto;
			background: url(http://codegen.caihongy.cn/20251120/57aeb3e6883c4844be15b3c9cf6764bb.jpg) fixed no-repeat center top / cover,#fff;
			width: 100%;
			position: relative;
			order: 4;
			.list_title_box {
				margin: 0 auto 20px;
				background: none;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 0px;
				border-style: solid;
				flex-wrap: wrap;
				.list_title {
					border: 4px double #fff;
					padding: 0;
					color: #fff;
					background: none;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 60px;
					text-align: center;
					order: 2;
				}
				.list_subhead {
					margin: 0;
					color: #fff;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 42px;
					line-height: 80px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -5px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list16 {
				padding: 0;
				margin: 0 0 0 -10px;
				color: #999;
				background: none;
				display: flex;
				width: calc(100% + 20px);
				font-size: 14px;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					padding: 10px;
					margin: 10px;
					background: #fff;
					display: flex;
					width: calc(50% - 20px);
					position: relative;
					flex-wrap: wrap;
					height: auto;
					.img {
						margin: 0 auto 5px;
						overflow: hidden;
						width: 100%;
						text-align: center;
						height: auto;
						img {
							border-radius: 0;
							margin: 0 auto;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 300px;
						}
					}
					.infoBox {
						overflow: hidden;
						height: auto;
						.info-left {
							align-content: flex-start;
							display: flex;
							width: 100%;
							justify-content: center;
							flex-wrap: wrap;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #333;
								white-space: nowrap;
								font-weight: 600;
								width: 100%;
								font-size: 15px;
								line-height: 26px;
								text-overflow: ellipsis;
								text-align: center;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								width: 100%;
								font-size: 18px;
								line-height: 1.5;
								text-align: center;
							}
							.time_item {
								padding: 0 10px;
								border-color: #f00;
								border-width: 0 0 0px 0;
								border-style: solid;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 10px;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 10px;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 10px;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 10px;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
						.desc {
							color: #666;
							flex: 1;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
					}
				}
				.list-item:hover {
					cursor: pointer;
					box-shadow: 0px 0px 16px 0px rgba(3, 0, 0, 0.35);
					background: #fff;
					.infoBox {
						.info-left {
							.name {
								color: #ff6f3c;
							}
							.price {
								color: #f00;
							}
							.time_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.publisher_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.like_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.collect_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.view_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
						}
						.desc {
							color: #fff;
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #04a44a30;
				cursor: pointer;
				padding: 0px;
				margin: 0;
				display: block;
				line-height: 36px;
				right: calc((100% - 1200px)/2);
				border-radius: 36px;
				top: 65px;
				background: none;
				width: auto;
				position: absolute;
				text-align: center;
				.text {
					margin: 0;
					color: #fff;
					font-size: 16px;
					order: 2;
				}
				.icon {
					color: #999;
					background: none;
					display: none;
					width: 23px;
					font-size: 16px;
					height: 7px;
				}
			}
		}
	}
</style>
