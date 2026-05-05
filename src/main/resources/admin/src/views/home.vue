<template>
	<div class="home-content" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				<span>欢迎使用</span>
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('yuelanshi','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-xiaoliang1"></span>
				</div>
				<div class="right">
					<div class="num">{{yuelanshiCount}}</div>
					<div class="name">阅览室总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('zuoweiyuyue','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-xiaoliang6"></span>
				</div>
				<div class="right">
					<div class="num">{{zuoweiyuyueCount}}</div>
					<div class="name">座位预约总数</div>
				</div>
			</div>
			<div id="statis3" class="statis3 animate__animated" v-if="isAuth('qiandaodengji','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-shuju20"></span>
				</div>
				<div class="right">
					<div class="num">{{qiandaodengjiCount}}</div>
					<div class="name">签到登记总数</div>
				</div>
			</div>
			<div id="statis4" class="statis4 animate__animated" v-if="isAuth('qiantuijilu','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-tongji7"></span>
				</div>
				<div class="right">
					<div class="num">{{qiantuijiluCount}}</div>
					<div class="name">签退记录总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
		<div id="news-box" class="news-box animate__animated">
			<div class="news-title">
				公告资讯
			</div>
			<div class="news-list">
				<div class="news-item" v-for="(item,index) in newsList" :index="index" @click="newsDetailClick(item)">
					<div class="news-text">
						{{item.title}}
					</div>
					<div class="news-img-box">
						<img :src="item.picture?($base.url + item.picture.split(',')[0]):''" alt="">
					</div>
					<div class="news-desc">
						{{item.introduction}}
					</div>
					<div class="news-time">
						{{item.addtime}}
					</div>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="newsVisible" title="公告资讯" :append-to-body="true" width="60%">
			<div style="width: 100%;text-align: center;font-size: 20px;color: #000;padding: 0 0 20px;font-weight: bold;">{{newsDetail.title}}</div>
			<div style="width: 100%;">
				<div class="ql-snow ql-editor" v-html="newsDetail.content"></div>
			</div>
		</el-dialog>
	
		<!-- echarts -->
		<!-- 4 -->
		<div class="type4">
			<div class="echarts1 animate__animated" v-if="isAuth('yuelanshi','首页统计',2)">
				<div id="yuelanshiChart1" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts2 animate__animated" v-if="isAuth('zuoweiyuyue','首页统计',2)">
				<div id="zuoweiyuyueChart1" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts3 animate__animated" v-if="isAuth('qiandaodengji','首页统计',2)">
				<div id="qiandaodengjiChart1" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts4 animate__animated" v-if="isAuth('qiantuijilu','首页统计',2)">
				<div id="qiantuijiluChart1" style="width: 100%;height: 100%"></div>
			</div>
		</div>
		<!-- echarts -->
	</div>
</template>
<script>
import 'animate.css'
//4
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			yuelanshiCount: 0,
			zuoweiyuyueCount: 0,
			qiandaodengjiCount: 0,
			qiantuijiluCount: 0,
			line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#7b4ffb","#dc3dfa","#76bd9e","#3b43f7","#73c0de","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"pin","symbolSize":20},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
			bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#7b4ffb","#dc3dfa","#76bd9e","#3b43f7","#73c0de","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"grid":{"right":"20","top":"60","left":"20","bottom":"20","containLabel":true},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"barBorderRadius":[5,5,0,0],"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"30%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"subtext":"","shadowBlur":0,"bottom":"auto","show":true,"right":"auto","subtextStyle":{"padding":[5,0,0,0],"borderColor":"red","color":"red","borderWidth":10},"top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
			pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#7b4ffb","#dc3dfa","#76bd9e","#3b43f7","#73c0de","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":4,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":4,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#964afb","shadowBlur":0,"width":2,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#7b4ffb","#dc3dfa","#76bd9e","#3b43f7","#73c0de","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":4,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":4,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			boardBase: {"funnelNum":8,"lineNum":8,"radarNum":8,"gaugeNum":8,"barNum":8,"pieNum":8},
			gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#7b4ffb","#dc3dfa","#76bd9e","#3b43f7","#73c0de","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
			radar: {"backgroundColor":"transparent","radar":{"shape":"circle"},"color":["#365E77","#DF308C","#0CB906","#7690cb","#49ada0","#5BBAEC","#a68a28","#EE142F","#FFE9E9"],"legend":{"padding":5,"itemGap":5,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":4,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"auto","itemWidth":4,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#7987FD"}},"title":{"top":"top","left":"left","textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#666","textShadowBlur":0}}},
			newsList: [],
			newsDetail: {},
			newsVisible: false,
			indexBgUrl: '',
			indexLogoUrl: '',
		};
	},
	mounted(){
		this.init();
		this.getyuelanshiCount();
		if(this.isAuth('yuelanshi','首页统计',2)){
			this.yuelanshiChat1();
		}
		this.getzuoweiyuyueCount();
		if(this.isAuth('zuoweiyuyue','首页统计',2)){
			this.zuoweiyuyueChat1();
		}
		this.getqiandaodengjiCount();
		if(this.isAuth('qiandaodengji','首页统计',2)){
			this.qiandaodengjiChat1();
		}
		this.getqiantuijiluCount();
		if(this.isAuth('qiantuijilu','首页统计',2)){
			this.qiantuijiluChat1();
		}
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	created() {
		this.$http.get('config/info?name=bIndexBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.$http.get('config/info?name=bHomeLogo',).then(rs=>{this.indexLogoUrl = rs.data.data?rs.data.data.value:''})
	},
	computed: {
		sessionForm() {
			return JSON.parse(this.$storage.getObj('userForm'))
		},
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__'},
				{id:'statis1',css:'animate__'},
				{id:'statis2',css:'animate__'},
				{id:'statis3',css:'animate__'},
				{id:'statis4',css:'animate__'},
				{id:'news-box',css:'animate__'},
				{id:'yuelanshiChart1',css:'animate__'},
				{id:'zuoweiyuyueChart1',css:'animate__'},
				{id:'qiandaodengjiChart1',css:'animate__'},
				{id:'qiantuijiluChart1',css:'animate__'},
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
		// 统计图动画
		myChartInterval(type, xAxisData, seriesData, myChart) {
			this.$nextTick(() => {
				setInterval(() => {
					let xAxis = xAxisData.shift()
					xAxisData.push(xAxis)
					let series = seriesData.shift()
					seriesData.push(series)
				
					if (type == 1) {
						myChart.setOption({
							xAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
					if (type == 2) {
						myChart.setOption({
							yAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
				}, $template2.back.board.bar.base.interval);
			})
		},
		changeStatQuery(arr) {
			if(arr.length==1) {
				if(arr[0] == 'users'&&this.$storage.get("sessionTable")=='users') {
					return true
				}
			}
			let role = this.$storage.get('role')
			for(let x in arr) {
				if(arr[x] == role) {
					return true
				}
			}
			return false
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
			this.getNewsList()
		},
		getNewsList(){
			let params = {
				page: 1,
				limit: 10,
				sort: 'addtime',
				order: 'desc',
			}
			this.$http({
				url: `news/list`,
				method: "get",
				params: params
			}).then(res => {
				if(res.data&&res.data.code==0){
					this.newsList = res.data.data.list
				}
			});
		},
		newsDetailClick(row){
			this.newsDetail = row
			this.newsVisible = true
		},
		getyuelanshiCount() {
			this.$http({
				url: `yuelanshi/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.yuelanshiCount = data.data
				}
			})
		},
		yuelanshiChat1(e=null) {
			this.$nextTick(()=>{
				var yuelanshiChart1 = echarts.init(document.getElementById("yuelanshiChart1"),'macarons');
				let params = {
				}
				this.$http({
					url: `yuelanshi/value/mingcheng/clicknum`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						// 统计图设置对了吗
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].mingcheng);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].mingcheng
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '阅览室浏览统计'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						yuelanshiChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							yuelanshiChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getzuoweiyuyueCount() {
			this.$http({
				url: `zuoweiyuyue/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.zuoweiyuyueCount = data.data
				}
			})
		},
		zuoweiyuyueChat1(e=null) {
			this.$nextTick(()=>{
				var zuoweiyuyueChart1 = echarts.init(document.getElementById("zuoweiyuyueChart1"),'macarons');
				let params = {
				}
				if(params.conditionColumn) {
					params.conditionColumn += ';' + 'sfsh'
					params.conditionValue += ';' + '是'
				}else {
					params.conditionColumn = 'sfsh'
					params.conditionValue = '是'
				}
				this.$http({
					url: "zuoweiyuyue/group/reservationdate",
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						// 统计图设置对了吗
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].reservationdate);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].reservationdate
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '预约统计'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: '55%',
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						zuoweiyuyueChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							zuoweiyuyueChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getqiandaodengjiCount() {
			this.$http({
				url: `qiandaodengji/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.qiandaodengjiCount = data.data
				}
			})
		},
		qiandaodengjiChat1(e=null) {
			this.$nextTick(()=>{
				var qiandaodengjiChart1 = echarts.init(document.getElementById("qiandaodengjiChart1"),'macarons');
				let params = {
				}
				if(params.conditionColumn) {
					params.conditionColumn += ';' + 'sfsh'
					params.conditionValue += ';' + '是'
				}else {
					params.conditionColumn = 'sfsh'
					params.conditionValue = '是'
				}
				this.$http({
					url: "qiandaodengji/group/qiandaoshijian",
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						// 统计图设置对了吗
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].qiandaoshijian);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].qiandaoshijian
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '用户签到统计'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: '55%',
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						qiandaodengjiChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							qiandaodengjiChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getqiantuijiluCount() {
			this.$http({
				url: `qiantuijilu/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.qiantuijiluCount = data.data
				}
			})
		},
		qiantuijiluChat1(e=null) {
			this.$nextTick(()=>{
				var qiantuijiluChart1 = echarts.init(document.getElementById("qiantuijiluChart1"),'macarons');
				let params = {
				}
				this.$http({
					url: "qiantuijilu/group/qiantuishijian",
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						// 统计图设置对了吗
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].qiantuishijian);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].qiantuishijian
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '用户签退统计'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let seriesObj = {
							data: yAxis,
							type: 'bar',
							coordinateSystem: 'polar',
							label: {
								show: true,
								position: 'middle',
								formatter: '{b}: {c}'
							}
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							polar: {
								radius: [0, '80%']
							},
							angleAxis: {
								// max: 'auto',
								startAngle: 75
							},
							radiusAxis: {
								type: 'category',
								data: xAxis
							},
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						qiantuijiluChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							qiantuijiluChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 0 30px 30px;
		background: url(http://codegen.caihongy.cn/20250919/c6b9a86674164f9a82aa4440bd68c724.png) no-repeat center top / cover;
		display: flex;
		width: 100%;
		min-height: 100vh;
		flex-wrap: wrap;
		.home-title {
			padding: 10px 0 0;
			box-shadow: none;
			margin: 10px 0 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0;
				color: #333;
				font-size: 30px;
				line-height: 60px;
				span {
				}
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
		}
		.statis-box {
			margin: 20px 0;
			background: none;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.statis1 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis1:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis2 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis2:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis3 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis3:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis4 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis4:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis5 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis5:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis6 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis6:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis7 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis7:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis8 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis8:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis9 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis9:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis10 {
				border: 1px solid #00000050;
				border-radius: 5px;
				box-shadow: 0px 4px 3px #8558ff50;
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: flex;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #dc3dfa;
						background: none;
						font-weight: 500;
						width: 48px;
						font-size: 48px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #7b4ffb;
						font-weight: 600;
						font-size: 36px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #00000050;
						font-size: 14px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis10:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
		}
		.news-box {
			border: 1px solid #00000050;
			padding: 10px 20px;
			margin: 10px;
			display: flex;
			transition: 0.3s;
			border-radius: 10px;
			box-shadow: 0px 4px 3px #8558ff50;
			flex-direction: column;
			flex: 1.5;
			background: #fff;
			width: calc(50% - 20px);
			justify-content: center;
			align-items: center;
			order: 12;
			.news-title {
				color: #000;
				font-weight: 600;
				width: 100%;
				font-size: 22px;
				line-height: 1;
			}
			.news-list {
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: center;
				flex-wrap: wrap;
			}
			.news-item {
				border: 0px solid #99999950;
				cursor: pointer;
				padding: 10px 0 0;
				margin: 15px 0 0;
				display: flex;
				width: calc(50% - 20px);
				border-width: 0px 0 0;
				justify-content: space-between;
				align-items: center;
				.news-text {
					overflow: hidden;
					color: #000;
					white-space: nowrap;
					flex: 1;
					font-weight: 500;
					width: 100%;
					font-size: 15px;
					line-height: 2;
					text-overflow: ellipsis;
				}
				.news-img-box {
					margin: 0 10px 0 0;
					overflow: hidden;
					display: none;
					width: auto;
					height: auto;
					img {
						object-fit: cover;
						width: 60px;
						height: 60px;
					}
				}
				.news-desc {
					overflow: hidden;
					color: #666;
					white-space: nowrap;
					flex: 1;
					display: none;
					width: 100%;
					text-overflow: ellipsis;
				}
				.news-time {
					color: #999;
					font-size: 12px;
				}
			}
		}
		.news-box:hover {
			box-shadow: 0px 4px 3px #8558ff50;
			transform: translate3d(0, 3px, 0);
			z-index: 1;
			background: rgba(255,255,255,1);
		}
		// echarts4
		.type4 {
			padding: 0;
			align-content: flex-start;
			background: none;
			display: flex;
			width: 100%;
			justify-content: space-between;
			flex-wrap: wrap;
			height: auto;
			.echarts1 {
				border: 1px solid #00000050;
				border-radius: 8px;
				padding: 20px;
				box-shadow: 0px 4px 3px #8558ff50;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts1:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.echarts2 {
				border: 1px solid #00000050;
				border-radius: 8px;
				padding: 20px;
				box-shadow: 0px 4px 3px #8558ff50;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts2:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.echarts3 {
				border: 1px solid #00000050;
				border-radius: 8px;
				padding: 20px;
				box-shadow: 0px 4px 3px #8558ff50;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts3:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.echarts4 {
				border: 1px solid #00000050;
				border-radius: 8px;
				padding: 20px;
				box-shadow: 0px 4px 3px #8558ff50;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts4:hover {
				box-shadow: 0px 4px 3px #8558ff50;
				transform: translate3d(0, 3px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
