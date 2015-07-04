import java.util.Random;


public class Shudu {	
	  int[][] data;     //用于中间计算以及储存用户输入
	  int[][] answer;   
	  int[][] original;  //储存初始棋盘
     
	  //标示数组, 标示当前某行,某列或者某个块内是否有数字x;
	  boolean[][] colSign;
	  boolean[][] rowSign;
	  boolean[][] blockSign;
	  
	  Shudu(){
		  data = new int[9][9];
		  answer = new int[9][9];
          original = new int[9][9];
		  
		  colSign = new boolean[9][10];
		  rowSign = new boolean[9][10];
		  blockSign = new boolean[9][10];		  
		  clear();
	  }
	  
	  public void clear(){
		  for(int i = 0; i < 9; i ++){
			  for(int j = 0; j < 9; j ++){
				   data[i][j] = answer[i][j] = original[i][j] = 0;   
			   }
		  }
		  for(int i = 0; i < 9; i ++){
			  for(int j = 0; j < 10; j ++){
				  colSign[i][j] = rowSign[i][j] = blockSign[i][j] = false;
			  }
		  }
	   }      
	  
	   //chess为需要解决的数独
	   public boolean solve(int[][] chess, int index){
		    if(index == 0)
		    	initSign(chess);   //初始化行列块的标示
		    		   
		    if(index == 81)
		    	return true;

		    int row = index / 9;
		    int col = index % 9;
		    if(chess[row][col] != 0){       
		    	return solve(chess,index + 1);
		    }
		    
		    boolean flag = false;
			for(int n = 1; n <= 9; n++){
				 chess[row][col] = n;
				 if (!colSign[col][n] && !rowSign[row][n] && !blockSign[(row / 3) * 3 + col / 3][n]){
					colSign[col][n] = true;
					rowSign[row][n] = true;
					blockSign[(row / 3) * 3 + col / 3][n] = true;
					if(solve(chess, index + 1)){
			        	flag = true;
			        	break;
			        }
					colSign[col][n] = false;
					rowSign[row][n] = false;
					blockSign[(row / 3) * 3 + col / 3][n] = false;
			     }
			}
			if(!flag)
				chess[row][col] = 0;
		    return flag;
	   }
	   
	   //生成某个难度的数独， 现在每个3*3块内随机生成4个合法的数， 
	   //之后检查是否有解，无解则继续循环至有解。运行速度可以接受。
	   public void generate(int difficulty){
			Random random = new Random();
			
			while (true) {
				clear();
				
				while (!set(0, 0, random.nextInt(9) + 1));
				while (!set(2, 0, random.nextInt(9) + 1));
				while (!set(0, 2, random.nextInt(9) + 1));
				while (!set(2, 2, random.nextInt(9) + 1));
				
				while (!set(0, 6, random.nextInt(9) + 1));
				while (!set(2, 6, random.nextInt(9) + 1));
				while (!set(0, 8, random.nextInt(9) + 1));
				while (!set(2, 8, random.nextInt(9) + 1));
		
				while (!set(3, 3, random.nextInt(9) + 1));
				while (!set(3, 5, random.nextInt(9) + 1));
				while (!set(5, 3, random.nextInt(9) + 1));
				while (!set(5, 5, random.nextInt(9) + 1));
				
				while (!set(6, 0, random.nextInt(9) + 1));
				while (!set(6, 2, random.nextInt(9) + 1));
				while (!set(8, 0, random.nextInt(9) + 1));
				while (!set(8, 2, random.nextInt(9) + 1));
				
				while (!set(6, 6, random.nextInt(9) + 1));
				while (!set(8, 6, random.nextInt(9) + 1));
				while (!set(6, 8, random.nextInt(9) + 1));
				while (!set(8, 8, random.nextInt(9) + 1));
				
				if(solve(data, 0))  break;		
			}
		   
            for(int i = 0; i < 9; i ++){
            	for(int j = 0; j < 9; j ++){
            		answer[i][j] = data[i][j];
            	}
            }
            for(int i = 0; i < 9; i ++){
            	for(int j = 0; j < 9; j ++){
            		original[i][j] = data[i][j] = 0;
            	}
            }
            //设置空位
			for (int i = 0; i < 9; i += 3) {
				for (int j = 0; j < 9; j += 3) {
					for (int n = 0; n < difficulty; n++) {
						int ran = random.nextInt(9);
						int x = i + (ran / 3);
						int y = j + (ran % 3);
						if (data[x][y] == 0) { //防止重复在一个位置填
							original[x][y] = data[x][y] = answer[x][y];
						}
						else {
							n--;
						}
					}
				}
			}
			initSign(original);   
	   }
	   void generate(int s[][]){
		   for(int i = 0; i < 9; i ++){
			   for(int j = 0; j < 9; j ++){
				   original[i][j] = data[i][j] = answer[i][j] = s[i][j];
			   }
		   }
		   this.solve(answer, 0);
		   initSign(original);   
	   }
	   
	   
	   public void initSign(int[][] chess){	
			for(int i = 0; i < 9; i ++){
				for(int j = 0; j < 10; j ++){
					colSign[i][j] = rowSign[i][j] = blockSign[i][j] = false;
				}
			}
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (chess[i][j] != 0) {
						rowSign[i][chess[i][j]] = true;						
						colSign[j][chess[i][j]] = true;
						blockSign[(i / 3) * 3 + j / 3][chess[i][j]] = true;
					}
				}
			}   
	   }
        //直接修改data数组， 不检查合法性
	    public void setWithoutCheck(int i, int j, int n){
	    	data[i][j] = n;  	
	    }
	    //尝试摆放,会进行合法性检查
		public boolean set(int i, int j, int n) {
			if (n > 9 || n < 1)
				return false;		
			if ( !rowSign[i][n] && !colSign[j][n] && !blockSign[(i / 3) * 3 + j / 3][n]) {
				rowSign[i][n] = true;				
				colSign[j][n] = true;
				blockSign[(i / 3) * 3 + j / 3][n] = true;			
				data[i][j] = n;
				return true;
			}
			else 
				return false;
	   }
		//检查结果是否正确
		public int check() {
			initSign(original);		
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (original[i][j] == 0) {
						if(data[i][j] == 0){
							return -2;
						}
						if (!this.set(i, j, data[i][j])) {
							return i * 9 + j;
						}
					}
				}
			}
			return -1;
	   }
		
	   public void show(){
		   System.out.println("original");
		   for(int i = 0; i < 9; i ++){
			   for(int j = 0; j < 9; j ++){
				   System.out.print(data[i][j] + " ");
			   }
			   System.out.println();
		   }
		   System.out.println("answer");
		   for(int i = 0; i < 9; i ++){
			   for(int j = 0; j < 9; j ++){
				   System.out.print(answer[i][j] + " ");
			   }
			   System.out.println();
		   }
	   }
}
