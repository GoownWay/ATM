package frame;

public class Main {//整个程序的主函数

	public static void main(String[] args) {
		WelcomeFrame f = new WelcomeFrame(); //初始化欢迎界面
		f.show();//调用欢迎界面显示方法
		f.listen();//调用欢迎界面里的按钮监听方法
	}
}