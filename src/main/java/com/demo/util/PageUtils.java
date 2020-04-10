package com.demo.util;


public class PageUtils {

	private int currentPage = 1;// 当前页
	
	private int showLinePage = 10;//每页显示的记录数
	
	private int totalSize = 0;//总记录数

	private int startNum = 1;//开始记录数
	
	private int endNum = 1;//结束记录数
	
	private String pageInfo="";// 分页信息
	
	private int pageNum = 1;//页数

	public String getPage(String url) {
		StringBuffer sb = new StringBuffer();
		if (totalSize > 0 && currentPage > 0 && url != null
				&& !url.trim().equals("")) {
			// 获得总页数
			long count = totalSize <= showLinePage ? 1 : totalSize / showLinePage;
			if (totalSize > showLinePage && totalSize % showLinePage != 0) {
				count += 1;
			}
			String temp = url.indexOf("?") != -1 ? "&" : "?";
			if (showLinePage == 20) {
				sb
						.append("显示<a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=20' class='accut' >20</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=50' >50</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=100' >100</a> 行 ");

			} else if (showLinePage == 50) {
				sb
						.append("显示<a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=20' >20</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=50' class='accut' >50</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=100' >100</a> 行 ");
			} else {
				sb
						.append("显示<a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=20' >20</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=50' >50</a> ");
				sb
						.append(" <a href='"
								+ url
								+ temp
								+ "pageNum=1&showLinePage=100' class='accut'>100</a>  行");
			}
			sb
					.append("<input type='hidden' name='jspUtil.showLinePage' id='showLinePage' value='"
							+ showLinePage + "'>");
			if (count != 1) {
				sb.append("<a href='" + url + temp + "pageNum=1&showLinePage="
						+ showLinePage + "'><<</a>");

				if (count < 9) {
					for (int i = 1; i <= count; i++) {
						if (i != currentPage) {
							sb.append("<a href='" + url + temp + "pageNum=" + i
									+ "&showLinePage=" + showLinePage + "'>" + i
									+ "</a>");
						} else {
							sb.append("<a href='" + url + temp + "pageNum=" + i
									+ "&showLinePage=" + showLinePage + "' class='accut'>" + i
									+ "</a>");
						}
					}

					count = currentPage + 1 > count ? count : currentPage + 1;
					sb.append("<a href='" + url + temp + "pageNum=" + count
							+ "&showLinePage=" + showLinePage + "'>></a>");
				} else {
					if (currentPage < 4) {
						int leng = 8;
						if (currentPage != 1) {
							leng = 7;
							sb.append("<a href='" + url + temp + "pageNum="
									+ (currentPage - 1) + "&showLinePage="
									+ showLinePage + "'><</a>");
						}
						for (int i = 1; i < leng; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageNum=" + i
										+ "&showLinePage=" + showLinePage + "' class='accut'>" + i
										+ "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageNum="
										+ i + "&showLinePage=" + showLinePage + "'>"
										+ i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageNum=" + count
								+ "&showLinePage=" + showLinePage + "'>..." + count
								+ "</a>");
						count = currentPage + 1 > count ? count
								: currentPage + 1;
						sb.append("<a href='" + url + temp + "pageNum=" + count
								+ "&showLinePage=" + showLinePage + "'>></a>");
					} else if (currentPage >= 4 && currentPage != count) {
						sb.append("<a href='" + url + temp
								+ "pageNum=1&showLinePage=" + showLinePage
								+ "'>1...</a>");
						sb.append("<a href='" + url + temp + "pageNum="
								+ (currentPage - 1) + "&showLinePage=" + showLinePage
								+ "'><</a>");
						long j = currentPage - 2 + 5;
						j = j > count ? count : j;
						int i = currentPage - 2;
						if ((j - i) < 5) {
							i -= 5 - (j - i);
						}
						for (; i < j; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageNum=" + i
										+ "&showLinePage=" + showLinePage + "' class='accut'>" + i
										+ "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageNum="
										+ i + "&showLinePage=" + showLinePage + "'>"
										+ i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageNum=" + count
								+ "&showLinePage=" + showLinePage + "'>..." + count
								+ "</a>");
						count = currentPage + 1 > count ? count
								: currentPage + 1;
						sb.append("<a href='" + url + temp + "pageNum=" + count
								+ "&showLinePage=" + showLinePage + "'>></a>");
					} else {
						sb.append("<a href='" + url + temp
								+ "pageNum=1&showLinePage=" + showLinePage
								+ "'>1...</a>");
						sb.append("<a href='" + url + temp + "pageNum="
								+ (currentPage - 1) + "&showLinePage=" + showLinePage
								+ "'><</a>");
						for (long i = count - 6; i < count; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageNum=" + i
										+ "&showLinePage=" + showLinePage + "' class='accut'>" + i
										+ "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageNum="
										+ i + "&showLinePage=" + showLinePage + "'>"
										+ i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageNum=" + count
								+ "&showLinePage=" + showLinePage + "' class='accut'>" + count
								+ "</a>");
					}
				}
			}
			pageInfo = sb.toString();
		}
		return pageInfo;
	}

	/**
	 * @Description: 根据当前页，总记录数计算记录起始、截止数
	 */
	public void calculateRecordNum(){
		startNum = currentPage * showLinePage - showLinePage;
		endNum = 10;
	}
	public void calculateRecordNum_line(int line){
		showLinePage = line;
		startNum = currentPage * showLinePage - showLinePage;
		endNum = showLinePage;
	}
	
	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getShowLinePage() {
		return showLinePage;
	}

	public void setShowLinePage(int showLinePage) {
		this.showLinePage = showLinePage;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
}
