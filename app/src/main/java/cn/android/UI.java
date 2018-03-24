package cn.android;
import android.app.Application;

public class UI extends Application
{
		private UI This;
		@Override
		public void onCreate()
		{
				setTheme(R.style.AppUI);
				super.onCreate();
		}
		public UI Init(){
				if(This == null) {
						This = new UI();
				}
				return This;
		}
}
