package net.jeeshop.web.action.manage.activity;import net.jeeshop.core.BaseAction;import net.jeeshop.core.util.DateTimeUtil;import net.jeeshop.services.manage.activity.ActivityService;import net.jeeshop.services.manage.activity.bean.Activity;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public class ActivityAction extends BaseAction<Activity> {	private static final long serialVersionUID = 1L;	private ActivityService activityService;	private String[] selectAccountRange;	private static final Logger logger = LoggerFactory.getLogger(ActivityAction.class);	public String[] getSelectAccountRange() {		return selectAccountRange;	}	public void setSelectAccountRange(String[] selectAccountRange) {		this.selectAccountRange = selectAccountRange;	}	public ActivityService getActivityService() {		return activityService;	}	protected void selectListAfter() {		pager.setPagerUrl("activity!selectList.action");	}	public void setActivityService(ActivityService activityService) {		this.activityService = activityService;	}	public Activity getE() {		return this.e;	}	public void prepare() throws Exception {		if (this.e == null) {			this.e = new Activity();		}	}	public void insertAfter(Activity e) {		e.clear();	}		@Override	public String toAdd() throws Exception {				if(selectAccountRange!=null){			for(int i=0;i<selectAccountRange.length;i++){				selectAccountRange[i] = null;			}			selectAccountRange = null;		}		return super.toAdd();	}		@Override	public String toEdit() throws Exception {		e = getServer().selectOne(getE());		selectAccountRange = e.getAccountRange().split(",");		for(int i=0;i<selectAccountRange.length;i++){			//去除因struts2控件提交导致的空格			selectAccountRange[i]  = selectAccountRange[i].trim();			logger.error("selectAccountRange[i]="+selectAccountRange[i]);		}		return toEdit;	}		@Override	public String selectList() throws Exception {		super.selectList();				if(getPager()!=null && getPager().getList()!=null && getPager().getList().size()>0){			for(int i=0;i<getPager().getList().size();i++){				Activity activity = (Activity) getPager().getList().get(i);								activity.setExpire(activity.checkActivity());				if(!activity.isExpire()){					//计算活动多久结束，是否已结束					activity.setActivityEndDateTime(DateTimeUtil.getActivityEndDateTimeString(activity.getEndDate()));									}			}		}				return toList;	}}