package 키오스크.JAVA.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import 키오스크.JAVA.util.Common;
import 키오스크.JAVA.vo.BasketVO;

public class BasketDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    public List<BasketVO> basketSelect() {
        List<BasketVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CAFE_BASKET ORDER BY BASKET_ID";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int basketId = rs.getInt("BASKET_ID");
                String menuName = rs.getString("MENU_NAME");
                int menuPrice = rs.getInt("MENU_PRICE");
                String optionName = rs.getString("OPTION_NAME");
                int optionPrice = rs.getInt("OPTION_PRICE");
                int cnt = rs.getInt("MENU_CNT");
                int totalPrice = rs.getInt("TOTAL_PRICE");

                BasketVO vo = new BasketVO(basketId, menuName, menuPrice, optionName, optionPrice, cnt, totalPrice);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void basketSelectPrint(List<BasketVO> list) {
        System.out.println("-----------------------------");
        for (BasketVO e : list) {
            System.out.println("장바구니 번호 : " + e.getBasketid());
            System.out.println("메뉴이름 : " + e.getMenuName());
            System.out.println("메뉴가격 : " + e.getMenuPrice());
            System.out.println("옵션 : " + e.getOptionName());
            System.out.println("옵션 가격 : " + e.getOptionPrice());
            System.out.println("수량 : " + e.getMenuCnt());
            System.out.println("총 금액 : " + e.getTotalPrice());
            System.out.println("-----------------------------");
        }
    }
    public void basketmenuDelete(String menuDeleteName){
                try {
                    conn = Common.getConnection();
                    stmt = conn.createStatement();
                    String sql = "DELETE FROM CAFE_BASKET WHERE MENU_NAME = '" + menuDeleteName + "'";
                    rs = stmt.executeQuery(sql);
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    public void basketDelete(){
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql1 = "DELETE FROM CAFE_BASKET";
            String sql2 = "DROP SEQUENCE BASKET_SEQ";
            String sql3 = "CREATE SEQUENCE BASKET_SEQ";
            rs = stmt.executeQuery(sql1);
            rs = stmt.executeQuery(sql2);
            rs = stmt.executeQuery(sql3);
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
