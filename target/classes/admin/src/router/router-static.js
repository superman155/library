	import Vue from 'vue';
//配置路由
	import VueRouter from 'vue-router'
	Vue.use(VueRouter);
//1.创建组件
	import Index from '@/views/index'
	import Home from '@/views/home'
	import Login from '@/views/login'
	import NotFound from '@/views/404'
	import UpdatePassword from '@/views/update-password'
	import pay from '@/views/pay'
	import register from '@/views/register'
	import center from '@/views/center'
	import yonghu from '@/views/modules/yonghu/list'
	import gongzuorenyuan from '@/views/modules/gongzuorenyuan/list'
	import zangshuleixing from '@/views/modules/zangshuleixing/list'
	import yuelanshi from '@/views/modules/yuelanshi/list'
	import zuoweiyuyue from '@/views/modules/zuoweiyuyue/list'
	import quxiaoyuyue from '@/views/modules/quxiaoyuyue/list'
	import qiandaodengji from '@/views/modules/qiandaodengji/list'
	import qiantuijilu from '@/views/modules/qiantuijilu/list'
	import xuexidazi from '@/views/modules/xuexidazi/list'
	import shenqingjiaru from '@/views/modules/shenqingjiaru/list'
	import news from '@/views/modules/news/list'
	import forum from '@/views/modules/forum/list'
	import forumreport from '@/views/modules/forumreport/list'
	import chat from '@/views/modules/chat/list'
	import syslog from '@/views/modules/syslog/list'
	import users from '@/views/modules/users/list'
	import discussyuelanshi from '@/views/modules/discussyuelanshi/list'
import config from '@/views/modules/config/list'

//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/gongzuorenyuan',
		name: '工作人员',
		component: gongzuorenyuan
	}
	,{
		path: '/zangshuleixing',
		name: '藏书类型',
		component: zangshuleixing
	}
	,{
		path: '/yuelanshi',
		name: '阅览室',
		component: yuelanshi
	}
	,{
		path: '/zuoweiyuyue',
		name: '座位预约',
		component: zuoweiyuyue
	}
	,{
		path: '/quxiaoyuyue',
		name: '取消预约',
		component: quxiaoyuyue
	}
	,{
		path: '/qiandaodengji',
		name: '签到登记',
		component: qiandaodengji
	}
	,{
		path: '/qiantuijilu',
		name: '签退记录',
		component: qiantuijilu
	}
	,{
		path: '/xuexidazi',
		name: '学习搭子',
		component: xuexidazi
	}
	,{
		path: '/shenqingjiaru',
		name: '申请加入',
		component: shenqingjiaru
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/forum',
		name: '互动论坛',
		component: forum
	}
	,{
		path: '/forumreport',
		name: '论坛举报',
		component: forumreport
	}
	,{
		path: '/chat',
		name: '智能客服',
		component: chat
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/users',
		name: '管理员',
		component: users
	}
	,{
		path: '/discussyuelanshi',
		name: '阅览室评论',
		component: discussyuelanshi
	}
	,{
		path: '/config/:type',
		name: '配置管理',
		component: config
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
