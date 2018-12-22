package style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class Style {
	
	public Style(JTable table) {
		DefaultTableCellRenderer renderer;
		renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(!isSelected) {
					if(row%2==0) {
						cell.setForeground(new Color(170, 18, 18));
					}else {
						cell.setForeground(new Color(13, 113, 62));
					}
				}
				if(isSelected) {
					if(row%2==0) {
						table.setSelectionBackground(new Color(170, 18, 18));
						table.setSelectionForeground(Color.WHITE);
					}else {
						table.setSelectionBackground(new Color(13, 113, 62));
						table.setSelectionForeground(Color.WHITE);
					}
				}
				return cell;
			}
		};
		table.setDefaultRenderer(Object.class, renderer); 
		table.setRowHeight(25);
		table.setFont(new Font("HY엽서L", Font.PLAIN, 14));
		table.setBackground(Color.WHITE);
		table.getTableHeader().setBackground(new Color(13, 113, 62)); 
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("HY엽서L", Font.BOLD,20));
		
	}
	
	//라벨 스타일 변경
	public Style(JLabel lbl, int size) {
		lbl.setOpaque(true);
		lbl.setBackground(new Color(13, 113, 62));
		lbl.setForeground(Color.WHITE);
		lbl.setFont(new Font("HY엽서L", Font.BOLD,size));
	}
	
	//텍스트 필드 스타일 변경
	public Style(JTextField j, int size) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		j.setFont(font);
	}
	
	//버튼 스타일 변경
	public Style(JButton j, int size, Color bc, Color fc) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		j.setFont(font);
		j.setForeground(fc); //폰트 색상 
		j.setBackground(bc); //배경색상
	}
	
	//콤보 스타일 변경
	public Style(JComboBox combo, int size, Color bc, Color fc) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		combo.setFont(font);
		combo.setForeground(fc); //폰트 색상 
		combo.setBackground(bc); //배경색상
	}
	
	//라벨 여러개 스타일 변경
	public Style(JLabel lbl[],int size, Color bc, Color fc) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		for (int i = 0; i < lbl.length; i++) {
			lbl[i].setFont(font);
			lbl[i].setFont(font);
			lbl[i].setOpaque(true); //label만 배경색 설정할때 써야함
			lbl[i].setForeground(fc); //폰트 색상 
			lbl[i].setBackground(bc); //배경색상
		}
	}
		
	//텍스트 여러개 필드 스타일 변경
	public Style(JTextField j[], int size, Color bc, Color fc) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		for (int i = 0; i < j.length; i++) {
			j[i].setFont(font);
			j[i].setForeground(fc); //폰트 색상 
			j[i].setBackground(bc); //배경색상
		}
	}
		
	//버튼 여러개 스타일 변경
	public Style(JButton j[], int size, Color bc, Color fc) {
		Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		for (int i = 0; i < j.length; i++) {
			j[i].setFont(font);
			j[i].setForeground(fc); //폰트 색상 
			j[i].setBackground(bc); //배경색상
		}
	}
	
	//프레임 중앙 위치
	public Style(int w, int h, JFrame j) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dm = t.getScreenSize();
		int sw=dm.width;
		int sh=dm.height;
		//스크린 중앙 계산
		int x=(int)(0.5*(sw-w));
		int y=(int)(0.5*(sh-h));
		j.setBounds(x, y, w, h);
		//j.getContentPane().setBackground(new Color(255, 220, 226)); //배경색상
	}
	
	public Style(int w, int h, JDialog j) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dm = t.getScreenSize();
		int sw=dm.width;
		int sh=dm.height;
		//스크린 중앙 계산
		int x=(int)(0.5*(sw-w));
		int y=(int)(0.5*(sh-h));
		j.setBounds(x, y, w, h);
	}
}
