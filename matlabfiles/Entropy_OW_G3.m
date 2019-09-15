 function Entropy_OW_G3(x)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
% function B1weights = EntropyWeight(RB1)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
disp('��������Ȩ���жϾ���R(m��n)');
RB1=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','B5:H18');
disp(RB1);
[rows,cols]=size(RB1); % �������Ĵ�С,rowsΪ�������(���۶���_ר�ң�m��)��colsΪָ�����������ָ��_ָ���n�У�
k=1/log(rows);        % ��k

B=zeros(rows,cols);    %R�����׼��ΪB����
a = min(min(RB1));
b = max(max(RB1));
for i = 1:rows
    for j = 1:cols
        B(i,j) = (RB1(i,j)-a)/(b-a);  %����ָ�괦��
        %%B(i,j) = (b-R(i,j))/(b-a); %����ָ�괦��
    end
end

f=zeros(rows,cols);   % ��ʼ��fij
sumBycols=sum(B,1);   % ��������ÿһ��֮��(���Ϊһ��1*cols��������)
%����fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % ��ʼ��lnfij
%����lnfij
for i=1:rows
  for j=1:cols
      if f(i,j)==0
          lnfij(i,j)=0;
      else
          lnfij(i,j)=log(f(i,j));
      end
  end
end
Hj=-k*(sum(f.*lnfij,1)); % ������ֵHj
B1weights=(1-Hj)/(cols-sum(Hj));  %������Ȩweights
disp(['����Ȩ������Ȩ��Ϊ��',B1weights]);
str = {'��ָ��CȨ������WiΪ'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','A19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B1weights,'User Entry (OW_Entropy _G3)','B19');
    disp('finished');
%end

%function B2weights = EntropyWeight(RB2)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
disp('��������Ȩ���жϾ���R(m��n)');
RB2=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','K5:N18');
disp(RB2);
[rows,cols]=size(RB2); % �������Ĵ�С,rowsΪ�������(���۶���_ר�ң�m��)��colsΪָ�����������ָ��_ָ���n�У�
k=1/log(rows);        % ��k

B=zeros(rows,cols);    %R�����׼��ΪB����
a = min(min(RB2));
b = max(max(RB2));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB2(i,j)-a)/(b-a);  %����ָ�괦��
        %%B(i,j) = (b-R(i,j))/(b-a); %����ָ�괦��
    end
end

f=zeros(rows,cols);   % ��ʼ��fij
sumBycols=sum(B,1);   % ��������ÿһ��֮��(���Ϊһ��1*cols��������)
%����fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % ��ʼ��lnfij
%����lnfij
for i=1:rows
  for j=1:cols
      if f(i,j)==0
          lnfij(i,j)=0;
      else
          lnfij(i,j)=log(f(i,j));
      end
  end
end
Hj=-k*(sum(f.*lnfij,1)); % ������ֵHj
B2weights=(1-Hj)/(cols-sum(Hj));  %������Ȩweights
disp(['����Ȩ������Ȩ��Ϊ��',B2weights]);
str = {'��ָ��CȨ������WiΪ'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','J19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B2weights,'User Entry (OW_Entropy _G3)','K19');
    disp('finished');
%end


%function B3weights = EntropyWeight(RB3)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
disp('��������Ȩ���жϾ���R(m��n)');
RB3=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','Q5:R18');
disp(RB3);
[rows,cols]=size(RB3); % �������Ĵ�С,rowsΪ�������(���۶���_ר�ң�m��)��colsΪָ�����������ָ��_ָ���n�У�
k=1/log(rows);        % ��k

B=zeros(rows,cols);    %R�����׼��ΪB����
a = min(min(RB3));
b = max(max(RB3));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB3(i,j)-a)/(b-a);  %����ָ�괦��
        %%B(i,j) = (b-R(i,j))/(b-a); %����ָ�괦��
    end
end

f=zeros(rows,cols);   % ��ʼ��fij
sumBycols=sum(B,1);   % ��������ÿһ��֮��(���Ϊһ��1*cols��������)
%����fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % ��ʼ��lnfij
%����lnfij
for i=1:rows
  for j=1:cols
      if f(i,j)==0
          lnfij(i,j)=0;
      else
          lnfij(i,j)=log(f(i,j));
      end
  end
end
Hj=-k*(sum(f.*lnfij,1)); % ������ֵHj
B3weights=(1-Hj)/(cols-sum(Hj));  %������Ȩweights
disp(['����Ȩ������Ȩ��Ϊ��',B3weights]);
str = {'��ָ��CȨ������WiΪ'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','P19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B3weights,'User Entry (OW_Entropy _G3)','Q19');
    disp('finished');
%end


%function B4weights = EntropyWeight(RB4)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
disp('��������Ȩ���жϾ���R(m��n)');
RB4=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G3)','U5:V18');
disp(RB4);
[rows,cols]=size(RB4); % �������Ĵ�С,rowsΪ�������(���۶���_ר�ң�m��)��colsΪָ�����������ָ��_ָ���n�У�
k=1/log(rows);        % ��k

B=zeros(rows,cols);    %R�����׼��ΪB����
a = min(min(RB4));
b = max(max(RB4));
for i = 1:rows;
    for j = 1:cols;
        B(i,j) = (RB4(i,j)-a)/(b-a);  %����ָ�괦��
        %%B(i,j) = (b-R(i,j))/(b-a); %����ָ�괦��
    end
end

f=zeros(rows,cols);   % ��ʼ��fij
sumBycols=sum(B,1);   % ��������ÿһ��֮��(���Ϊһ��1*cols��������)
%����fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % ��ʼ��lnfij
%����lnfij
for i=1:rows
  for j=1:cols
      if f(i,j)==0
          lnfij(i,j)=0;
      else
          lnfij(i,j)=log(f(i,j));
      end
  end
end
Hj=-k*(sum(f.*lnfij,1)); % ������ֵHj
B4weights=(1-Hj)/(cols-sum(Hj));  %������Ȩweights
disp(['����Ȩ������Ȩ��Ϊ��',B4weights]);
str = {'��ָ��CȨ������WiΪ'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G3)','T19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',B4weights,'User Entry (OW_Entropy _G3)','U19');
    disp('finished');
end

