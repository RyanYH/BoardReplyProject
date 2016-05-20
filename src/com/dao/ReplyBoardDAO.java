package com.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



import java.io.*;
public class ReplyBoardDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   try
	   {
		   // XML 파일 읽기 
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   // DB 처리
   public static List<ReplyBoardVO> boardAllData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<ReplyBoardVO> list=session.selectList("boardAllData",map);
	   session.close();
	   return list;
   }
   public static int boardTotalPage()
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("boardTotalPage");
	   session.close();
	   return total;
   }
   public static ReplyBoardVO boardContentData(int no)
   {
	   SqlSession session=ssf.openSession(true); // autoCommit()
	   session.update("boardHitIncrement",no);
	   //session.commit();
	   session.close();
	   session=ssf.openSession();
	   ReplyBoardVO vo=session.selectOne("boardContentData", no);
	   session.close();
	   return vo;
   }
   public static int boardReplyCount(int bno)
   {
	   SqlSession session=ssf.openSession();
	   int count=session.selectOne("boardReplyCount", bno);
	   session.close();
	   return count;
   }
   public static List<ReplyVO> replyAllData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<ReplyVO> list=session.selectList("replyAllData", map);
	   session.close();
	   return list;
   }
   public static void replyInsert(ReplyVO vo)
   {
	   SqlSession session=ssf.openSession(true);
	   session.insert("replyInsert",vo);
	   session.close();
   }
   public static void replyUpdate(ReplyVO vo)
   {
	   SqlSession session=ssf.openSession(true);
	   session.update("replyUpdate",vo);
	   session.close();
   }
   public static ReplyVO replyParentData(int no)
   {
   	SqlSession session=ssf.openSession();
   	ReplyVO vo=session.selectOne("replyParentData", no);
   	session.close();
   	return vo;
   }
   public static void replyDataModify(ReplyVO vo)
   {
   	SqlSession session=ssf.openSession(true);
   	session.update("replyDataModify", vo);
   	session.close();
   }
   public static void replyReInsert(ReplyVO vo)
   {
   	SqlSession session=ssf.openSession(true);
   	session.insert("replyReInsert", vo);
   	session.close();
   }
   public static void replyReDepthIncrement(int no)
   {
   	SqlSession session=ssf.openSession(true);
   	session.update("replyReDepthIncrement", no);
   	session.close();
   }
   public static void replyDepthDecrement(int no)
   {
   	SqlSession session=ssf.openSession(true);
   	session.update("replyDepthDecrement", no);
   	session.close();
   }
   public static void replyMsgUpdate(int no)
   {
   	SqlSession session=ssf.openSession(true);
   	session.update("replyMsgUpdate", no);
   	session.close();
   }
   public static void replyDelete(int no)
   {
   	SqlSession session=ssf.openSession(true);
   	session.delete("replyDelete", no);
   	session.close();
   }
   public static ReplyVO replyInfoData(int no)
   {
	   SqlSession session=ssf.openSession();
	   ReplyVO vo=session.selectOne("replyInfoData", no);
	   session.close();
	   return vo;
   }
   public static String boardGetPwd(int no)
   {
	   SqlSession session=ssf.openSession();
	   String pwd=session.selectOne("boardGetPwd", no);
	   session.close();
	   return pwd;
   }
   public static void boardDelete(int no)
   {
	   SqlSession session=ssf.openSession(true);
	   session.delete("boardReplyDelete",no);
	   session.delete("boardDelete",no);
	   session.close();
   }
   
}






