 function Entropy_OW_G3(x)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
% function B1weights = EntropyWeight(RB1)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
disp('请输入熵权法判断矩阵R(m×n)');
RB1=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','B5:H18');
disp(RB1);
[rows,cols]=size(RB1); % 输入矩阵的大小,rows为对象个数(评价对象_专家，m行)，cols为指标个数（评价指标_指标层n列）
k=1/log(rows);        % 求k

B=zeros(rows,cols);    %R矩阵标准化为B矩阵
a = min(min(RB1));
b = max(max(RB1));
for i = 1:rows
    for j = 1:cols
        B(i,j) = (RB1(i,j)-a)/(b-a);  %高优指标处理
        %%B(i,j) = (b-R(i,j))/(b-a); %低优指标处理
    end
end

f=zeros(rows,cols);   % 初始化fij
sumBycols=sum(B,1);   % 输入矩阵的每一列之和(结果为一个1*cols的行向量)
%计算fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % 初始化lnfij
%计算lnfij
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
B1weights=(1-Hj)/(cols-sum(Hj));  %计算熵权weights
disp(['由熵权法计算权重为：',B1weights]);
str = {'各指标C权重向量Wi为'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','A19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B1weights,'User Entry (OW_Entropy _G3)','B19');
    disp('finished');
%end

%function B2weights = EntropyWeight(RB2)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
disp('请输入熵权法判断矩阵R(m×n)');
RB2=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','K5:N18');
disp(RB2);
[rows,cols]=size(RB2); % 输入矩阵的大小,rows为对象个数(评价对象_专家，m行)，cols为指标个数（评价指标_指标层n列）
k=1/log(rows);        % 求k

B=zeros(rows,cols);    %R矩阵标准化为B矩阵
a = min(min(RB2));
b = max(max(RB2));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB2(i,j)-a)/(b-a);  %高优指标处理
        %%B(i,j) = (b-R(i,j))/(b-a); %低优指标处理
    end
end

f=zeros(rows,cols);   % 初始化fij
sumBycols=sum(B,1);   % 输入矩阵的每一列之和(结果为一个1*cols的行向量)
%计算fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % 初始化lnfij
%计算lnfij
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
B2weights=(1-Hj)/(cols-sum(Hj));  %计算熵权weights
disp(['由熵权法计算权重为：',B2weights]);
str = {'各指标C权重向量Wi为'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','J19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B2weights,'User Entry (OW_Entropy _G3)','K19');
    disp('finished');
%end


%function B3weights = EntropyWeight(RB3)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
disp('请输入熵权法判断矩阵R(m×n)');
RB3=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','Q5:R18');
disp(RB3);
[rows,cols]=size(RB3); % 输入矩阵的大小,rows为对象个数(评价对象_专家，m行)，cols为指标个数（评价指标_指标层n列）
k=1/log(rows);        % 求k

B=zeros(rows,cols);    %R矩阵标准化为B矩阵
a = min(min(RB3));
b = max(max(RB3));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB3(i,j)-a)/(b-a);  %高优指标处理
        %%B(i,j) = (b-R(i,j))/(b-a); %低优指标处理
    end
end

f=zeros(rows,cols);   % 初始化fij
sumBycols=sum(B,1);   % 输入矩阵的每一列之和(结果为一个1*cols的行向量)
%计算fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % 初始化lnfij
%计算lnfij
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
B3weights=(1-Hj)/(cols-sum(Hj));  %计算熵权weights
disp(['由熵权法计算权重为：',B3weights]);
str = {'各指标C权重向量Wi为'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','P19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B3weights,'User Entry (OW_Entropy _G3)','Q19');
    disp('finished');
%end


%function B4weights = EntropyWeight(RB4)   % 熵权法求指标权重,R为输入矩阵,返回权重向量weights
disp('请输入熵权法判断矩阵R(m×n)');
RB4=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','U5:V18');
disp(RB4);
[rows,cols]=size(RB4); % 输入矩阵的大小,rows为对象个数(评价对象_专家，m行)，cols为指标个数（评价指标_指标层n列）
k=1/log(rows);        % 求k

B=zeros(rows,cols);    %R矩阵标准化为B矩阵
a = min(min(RB4));
b = max(max(RB4));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB4(i,j)-a)/(b-a);  %高优指标处理
        %%B(i,j) = (b-R(i,j))/(b-a); %低优指标处理
    end
end

f=zeros(rows,cols);   % 初始化fij
sumBycols=sum(B,1);   % 输入矩阵的每一列之和(结果为一个1*cols的行向量)
%计算fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % 初始化lnfij
%计算lnfij
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
B4weights=(1-Hj)/(cols-sum(Hj));  %计算熵权weights
disp(['由熵权法计算权重为：',B4weights]);
str = {'各指标C权重向量Wi为'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','T19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B4weights,'User Entry (OW_Entropy _G3)','U19');
    disp('finished');
end

