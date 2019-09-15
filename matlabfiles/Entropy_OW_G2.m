function Entropy_OW_G2(x)   % ��Ȩ����ָ��Ȩ��,RΪ�������,����Ȩ������weights
disp('��������Ȩ���жϾ���R(m��n)');
RA=xlsread('D:\EclP\info_tmp.xlsx','User Entry (OW_Entropy _G2)','B5:E18');
disp(RA);
[rows,cols]=size(RA); % �������Ĵ�С,rowsΪ�������(���۶���_ר�ң�m��)��colsΪָ�����������ָ��_ָ���n�У�
k=1/log(rows);        % ��k

B=zeros(rows,cols);    %R�����׼��ΪB����
a = min(min(RA));
b = max(max(RA));
for i = 1:rows
    for j = 1:cols
        B(i,j) = (RA(i,j)-a)/(b-a) ; %����ָ�괦������ָ����ֵԽ��Խ�ã�
        %%B(i,j) = (b-R(i,j))/(b-a)  %����ָ�괦������ָ����ֵԽ��Խ��
    end
end
%disp(B)

f=zeros(rows,cols);   % ��ʼ��fij
sumBycols=sum(B,1);   % ��������ÿһ��֮��(���Ϊһ��1*cols��������)
% ����fij
for i=1:rows
  for j=1:cols
      f(i,j)=B(i,j)./sumBycols(1,j);
  end
end

lnfij=zeros(rows,cols); % ��ʼ��lnfij
% ����lnfij
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
Aweights=(1-Hj)/(cols-sum(Hj));  %������Ȩweights
disp(['����Ȩ������Ȩ��Ϊ��',Aweights]);
str = {'��ָ��BȨ������WiΪ'};
    s = xlswrite('D:\EclP\info_tmp.xlsx',str,'User Entry (OW_Entropy _G2)','A19');
    s = xlswrite('D:\EclP\info_tmp.xlsx',Aweights,'User Entry (OW_Entropy _G2)','B19');
    disp('finished');
end