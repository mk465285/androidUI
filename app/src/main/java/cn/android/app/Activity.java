package cn.android.app;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import cn.android.R;
import cn.android.UI;
import com.githang.statusbar.StatusBarCompat;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.view.View.*;
import cn.android.dialog.*;

public abstract class Activity extends Activity
{
		
		public UI App;
		
		protected abstract int CreateView_Res()
		protected View CreateView_View(){return CreateView();}
		protected void Init(){}
		public void SetTheme(int style,int color,boolean lighting){
				StatusBarCompat.setStatusBarColor(this,getResources().getColor(color),lighting);
				setTheme(style);
		}
		
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
				if(App == null){
						App = ((UI) getApplication()).Init();
				}
				SetTheme(R.style.AppUI,R.color.skyblue,false);
				super.onCreate(savedInstanceState);
				if(CreateView_Res()!=0){
						setContentView(CreateView_Res());
				} else if(CreateView_View()!=null){
						setContentView(CreateView_View());
				}
				Init();
		}
		public Toast toast(Object obj){
				if(obj==null){
						obj="空指针";
				} 
				Toast mToast = Toast.makeText(this,obj.toString(),Toast.LENGTH_SHORT);
				//mToast.getView().setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
				mToast.setDuration(10000);
				return mToast;
		}
		public void ShowToast(Object Str){
				toast(Str).show();
		}
		private final synchronized SharedPreferences getPre(String name){
				synchronized(SharedPreferences.class){
						return getSharedPreferences(name,MODE_PRIVATE);
				}
		}
		protected View CreateView(){
				LinearLayout newLine = new LinearLayout(this);
				newLine.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
				return newLine;
		}
		public void SaveString(String key,String val){
				SaveString(getClass().getName(),key,val);
		}
		public void SaveString(String name,String key,String val){
				SharedPreferences.Editor edit = getPre(name).edit();
				edit.putString(key,val);
				edit.commit();
		}
		public void SaveBoolean(String name,String key,boolean val){
				SharedPreferences.Editor edit = getPre(name).edit();
				edit.putBoolean(key,val);
				edit.commit();
		}
		public void SaveBoolean(String key,boolean val){
				SaveBoolean(getClass().getName(),key,val);
		}
		public void SaveInteger(String name,String key,int val){
				SharedPreferences.Editor edit = getPre(name).edit();
				edit.putInt(key,val);
				edit.commit();
		}
		public void SaveInteger(String key,int val){
				SaveInteger(getClass().getName(),key,val);
		}
		public String getstring(String name,String key,String val){
				return getPre(name).getString(key,val);
		}
		public int getinteger(String name,String key,int val){
				return getPre(name).getInt(key,val);
		}
		public boolean getBoolean(String name,String key,boolean val){
				return getPre(name).getBoolean(key,val);
		}
		public String getstring(String key,String val){
				return getstring(getClass().getName(),key,val);
		}
		public int getinteger(String key,int val){
				return getinteger(getClass().getName(),key,val);
		}
		public boolean getBoolean(String key,boolean val){
				return getBoolean(getClass().getName(),key,val);
		}
		public CustomAlertDialog ShowAlert(String msg){
				return ShowAlert("",msg,"确定","",null,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg){
				return ShowAlert(title,msg,"确定","",null,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg,String btn1){
				return ShowAlert(title,msg,btn1,"",null,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg,String btn1,String btn2){
				return ShowAlert(title,msg,btn1,btn2,null,null);
		}
		public CustomAlertDialog ShowAlert(String msg,OnClickListener btn1Listenet){
				return ShowAlert("",msg,"确定","",btn1Listenet,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg,OnClickListener btn1Listenet){
				return ShowAlert(title,msg,"确定","",btn1Listenet,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg,String btn1,OnClickListener btn1Listenet){
				return ShowAlert(title,msg,btn1,"",btn1Listenet,null);
		}
		public CustomAlertDialog ShowAlert(String title,String msg,String btn1,String btn2,OnClickListener btn1Listenet,OnClickListener btn2Listenet){
				CustomAlertDialog Alert = new CustomAlertDialog(this).builder();
				if(!"".equals(title)){
						Alert.setTitle(title);
				}
				Alert.setMsg((!"".equals(msg))?msg:"");
				if(!"".equals(btn1)){
						Alert.setPositiveButton(btn1,btn1Listenet);
						if(!"".equals(btn2)){
								Alert.setNegativeButton(btn2,btn2Listenet);
						}
				}
				return Alert;
		}
}
