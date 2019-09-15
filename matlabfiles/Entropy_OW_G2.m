function Entropy_OW_G2(x)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
disp('请输入熵权法判断矩阵R(m×n)');
RA=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G2)','B5:E18');
disp(RA);
[rows,cols]=size(RA); % 输入矩阵的大小,rows为对象个数(评价对象_专家，m行)，cols为指标个数（评价指标_指标层n列）
k=1/log(rows);        % 求k

B=zeros(rows,cols);    %R矩阵标准化为B矩阵
a = min(min(RA));
b = max(max(RA));
for i = 1:rows
    for j = 1:cols
        B(i,j) = (RA(i,j)-a)/(b-a) ; %高优指标处理，正向指标数值越高越好，
        %%B(i,j) = (b-R(i,j))/(b-a)  %低优指标处理，负向指标数值越低越好
    end
end
%disp(B)

f=zeros(rows,cols);   % 初始化fij
sumBycols=sum(B,1);   % 输入矩阵的每一列之和(结果为一个1*cols的行向量)
% 计算fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % 初始化lnfij
% 计算lnfij
for i=1:rows
  for j=1:cols
      if f(i,j)==0
          lnfij(i,j)=0;
      else
          lnfij(i,j)=log(f(i,j));
      end
  end
end
Hj=-k*(sum(f.*lnfij,1)); % 计算熵值Hj
Aweights=(1-Hj)/(cols-sum(Hj));  %计算熵权weights
disp(['由熵权法计算权重为：',Aweights]);
str = {'各指标B权重向量Wi为'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G2)','A19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',Aweights,'User Entry (OW_Entropy _G2)','B19');
    disp('finished');
end