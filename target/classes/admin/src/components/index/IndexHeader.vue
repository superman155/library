<template>
	<div class="navbar">
		<div class="title">
			<span class="title-name">{{this.$project.projectName}}</span>
		</div>
		<div class="dropdown-box">
			<div class="el-dropdown-link">
				<el-image v-if="avatar" :src="avatar?this.$base.url + avatar : require('@/assets/img/avator.png')" fit="cover"></el-image>
				<span class="label">欢迎</span>
				<span class="nickname">{{this.$storage.get('adminName')}}</span>
			</div>
			<div class="top-el-dropdown-menu-2">
				<div class="item1" @click="handleCommand('')">首页</div>
				<div class="item2" @click="handleCommand('center')">个人中心</div>
				<div class="item3" v-if="this.$storage.get('sessionTable')!='users'" @click="handleCommand('front')">退出到前台</div>
				<div class="item4" @click="handleCommand('logout')">退出登录</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		Loading
	} from 'element-ui';
	import axios from 'axios';
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
				user: null,
				topLogoUrl: '',
			};
		},
		created() {
			this.$http.get('config/info?name=bTopLogo',).then(rs=>{this.topLogoUrl = rs.data.data?rs.data.data.value:''})
		},
		computed: {
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			},
		},
		mounted() {
		},
		methods: {
			handleCommand(name) {
				if (name == 'logout') {
					this.onLogout()
				} 
				else if (name == 'front') {
					this.onIndexTap()
				}
				else {
					let router = this.$router
					name = '/'+name
					router.push(name)
				}
			},
			async onLogout() {
				await this.$http.post(`${this.$storage.get('sessionTable')}/logout`).then(rs=>{
					let storage = this.$storage
					let router = this.$router
					storage.clear()
					this.$store.dispatch('tagsView/delAllViews')
					router.replace({
						name: "login"
					});
				})
			},
			onIndexTap(){
				localStorage.setItem("frontToken", localStorage.getItem("Token"));
				localStorage.setItem("frontRole", localStorage.getItem("role"));
				localStorage.setItem("frontSessionTable", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontHeadportrait", localStorage.getItem("headportrait"));
				localStorage.setItem("UserTableName", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontUserid", localStorage.getItem("userid"));
				localStorage.setItem("username", localStorage.getItem("adminName"));
				window.location.href = `${this.$base.indexUrl}`
			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		.title {
			margin: 0 auto 0 0;
			display: block;
			.title-name {
				padding: 0;
				color: #000;
				font-weight: 600;
				font-size: 26px;
				line-height: 44px;
				float: left;
			}
		}
		.dropdown-box {
			color: #666;
			display: flex;
			font-size: 16px;
			order: 11;
			.el-dropdown-link {
				display: none;
				align-items: center;
				.el-image {
					border-radius: 100%;
					margin: 0 10px;
					object-fit: cover;
					display: inline-block;
					width: 42px;
					height: 42px;
				}
				.label {
					color: #666;
					font-size: 16px;
					line-height: 32px;
				}
				.nickname {
					color: #666;
					font-size: 16px;
					line-height: 32px;
				}
			}
			.top-el-dropdown-menu-2 {
				display: flex;
				justify-content: center;
				align-items: center;
				div.item1 {
					padding: 0 3px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item1:hover {
					cursor: pointer;
					color: #964afb;
				}
				div.item2 {
					padding: 0 3px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item2:hover {
					cursor: pointer;
					color: #964afb;
				}
				div.item3 {
					padding: 0 3px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item3:hover {
					cursor: pointer;
					color: #964afb;
				}
				div.item4 {
					padding: 0 3px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item4:hover {
					cursor: pointer;
					color: #964afb;
				}
			}
		}
	}
</style>
