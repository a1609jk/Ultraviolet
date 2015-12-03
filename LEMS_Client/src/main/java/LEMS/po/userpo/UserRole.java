package LEMS.po.userpo;

/**
 * @author 宋益明
 * 
 * 用户类型：快递员、营业厅业务员、中转中心业务员、中转中心仓库管理人员
 * 		     总经理、管理员、财务人员
 */
public enum UserRole {
	Courier,
	BusinessClerk,
	TransferClerk,
	StoreManager,
	GeneralManager,
	Manager,
	FinanceClerk
	;
	
	/**
	 * @param role
	 * @return String
	 * 把UserRole转换成String类型
	 */
	public static String transfer(UserRole role){
		String r="";
		switch(role){
		case Courier:
			r="快递员";
			break;
		case BusinessClerk:
			r="营业厅业务员";
			break;
		case TransferClerk:
			r="中转中心业务员";
			break;
		case StoreManager:
			r="仓库管理人员";
			break;
		case GeneralManager:
			r= "总经理";
			break;
		case Manager:
			r="管理员";
			break;
		case FinanceClerk:
			r="财务人员";
			break;
		default:
			break;
		}
		return r;
	}
}
