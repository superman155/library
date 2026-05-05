import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import gongzuorenyuanList from '../pages/gongzuorenyuan/list'
import gongzuorenyuanDetail from '../pages/gongzuorenyuan/detail'
import gongzuorenyuanAdd from '../pages/gongzuorenyuan/add'
import zangshuleixingList from '../pages/zangshuleixing/list'
import zangshuleixingDetail from '../pages/zangshuleixing/detail'
import zangshuleixingAdd from '../pages/zangshuleixing/add'
import yuelanshiList from '../pages/yuelanshi/list'
import yuelanshiDetail from '../pages/yuelanshi/detail'
import yuelanshiAdd from '../pages/yuelanshi/add'
import zuoweiyuyueList from '../pages/zuoweiyuyue/list'
import zuoweiyuyueDetail from '../pages/zuoweiyuyue/detail'
import zuoweiyuyueAdd from '../pages/zuoweiyuyue/add'
import quxiaoyuyueList from '../pages/quxiaoyuyue/list'
import quxiaoyuyueDetail from '../pages/quxiaoyuyue/detail'
import quxiaoyuyueAdd from '../pages/quxiaoyuyue/add'
import qiandaodengjiList from '../pages/qiandaodengji/list'
import qiandaodengjiDetail from '../pages/qiandaodengji/detail'
import qiandaodengjiAdd from '../pages/qiandaodengji/add'
import qiantuijiluList from '../pages/qiantuijilu/list'
import qiantuijiluDetail from '../pages/qiantuijilu/detail'
import qiantuijiluAdd from '../pages/qiantuijilu/add'
import xuexidaziList from '../pages/xuexidazi/list'
import xuexidaziDetail from '../pages/xuexidazi/detail'
import xuexidaziAdd from '../pages/xuexidazi/add'
import shenqingjiaruList from '../pages/shenqingjiaru/list'
import shenqingjiaruDetail from '../pages/shenqingjiaru/detail'
import shenqingjiaruAdd from '../pages/shenqingjiaru/add'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import forumreportList from '../pages/forumreport/list'
import forumreportDetail from '../pages/forumreport/detail'
import forumreportAdd from '../pages/forumreport/add'
import discussyuelanshiList from '../pages/discussyuelanshi/list'
import discussyuelanshiDetail from '../pages/discussyuelanshi/detail'
import discussyuelanshiAdd from '../pages/discussyuelanshi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'gongzuorenyuan',
					component: gongzuorenyuanList
				},
				{
					path: 'gongzuorenyuanDetail',
					component: gongzuorenyuanDetail
				},
				{
					path: 'gongzuorenyuanAdd',
					component: gongzuorenyuanAdd
				},
				{
					path: 'zangshuleixing',
					component: zangshuleixingList
				},
				{
					path: 'zangshuleixingDetail',
					component: zangshuleixingDetail
				},
				{
					path: 'zangshuleixingAdd',
					component: zangshuleixingAdd
				},
				{
					path: 'yuelanshi',
					component: yuelanshiList
				},
				{
					path: 'yuelanshiDetail',
					component: yuelanshiDetail
				},
				{
					path: 'yuelanshiAdd',
					component: yuelanshiAdd
				},
				{
					path: 'zuoweiyuyue',
					component: zuoweiyuyueList
				},
				{
					path: 'zuoweiyuyueDetail',
					component: zuoweiyuyueDetail
				},
				{
					path: 'zuoweiyuyueAdd',
					component: zuoweiyuyueAdd
				},
				{
					path: 'quxiaoyuyue',
					component: quxiaoyuyueList
				},
				{
					path: 'quxiaoyuyueDetail',
					component: quxiaoyuyueDetail
				},
				{
					path: 'quxiaoyuyueAdd',
					component: quxiaoyuyueAdd
				},
				{
					path: 'qiandaodengji',
					component: qiandaodengjiList
				},
				{
					path: 'qiandaodengjiDetail',
					component: qiandaodengjiDetail
				},
				{
					path: 'qiandaodengjiAdd',
					component: qiandaodengjiAdd
				},
				{
					path: 'qiantuijilu',
					component: qiantuijiluList
				},
				{
					path: 'qiantuijiluDetail',
					component: qiantuijiluDetail
				},
				{
					path: 'qiantuijiluAdd',
					component: qiantuijiluAdd
				},
				{
					path: 'xuexidazi',
					component: xuexidaziList
				},
				{
					path: 'xuexidaziDetail',
					component: xuexidaziDetail
				},
				{
					path: 'xuexidaziAdd',
					component: xuexidaziAdd
				},
				{
					path: 'shenqingjiaru',
					component: shenqingjiaruList
				},
				{
					path: 'shenqingjiaruDetail',
					component: shenqingjiaruDetail
				},
				{
					path: 'shenqingjiaruAdd',
					component: shenqingjiaruAdd
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'forumreport',
					component: forumreportList
				},
				{
					path: 'forumreportDetail',
					component: forumreportDetail
				},
				{
					path: 'forumreportAdd',
					component: forumreportAdd
				},
				{
					path: 'discussyuelanshi',
					component: discussyuelanshiList
				},
				{
					path: 'discussyuelanshiDetail',
					component: discussyuelanshiDetail
				},
				{
					path: 'discussyuelanshiAdd',
					component: discussyuelanshiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
