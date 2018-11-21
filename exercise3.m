H = [0  1  0  0  0  1  1  0  0  0  0  0  0;
1  0  1  0  0  0  0  1  0  0  0  0  0;
0  1  0  1  0  0  0  0  1  0  0  0  0;
0  0  1  0  1  0  0  0  0  1  0  0  0;
0  0  0  1  0  1  0  0  0  0  1  0  0;
1  0  0  0  1  0  0  0  0  0  0  1  0;
1  0  0  0  0  0  0  1  0  0  0  1  1;
0  1  0  0  0  0  1  0  1  0  0  0  0;
0  0  1  0  0  0  0  1  0  1  0  0  1;
0  0  0  1  0  0  0  0  1  0  1  0  0;
0  0  0  0  1  0  0  0  0  1  0  1  1;
0  0  0  0  0  1  1  0  0  0  1  0  0;
0  0  0  0  0  0  1  0  1  0  1  0  0];

J = [0  0  1  0  0  1  1  0  0  0  0  0  0  0;
0  0  0  1  1  0  0  0  0  0  0  1  0  0;
1  0  0  0  0  0  0  0  0  0  0  0  1  1;
0  1  0  0  1  0  0  0  0  1  0  0  0  0;
0  1  0  1  0  0  0  0  0  0  1  0  0  0;
1  0  0  0  0  0  0  1  0  0  0  0  1  0;
1  0  0  0  0  0  0  1  0  0  0  0  0  1;
0  0  0  0  0  1  1  0  1  0  0  0  0  0;
0  0  0  0  0  0  0  1  0  0  0  0  1  1;
0  0  0  1  0  0  0  0  0  0  1  1  0  0;
0  0  0  0  1  0  0  0  0  1  0  1  0  0;
0  1  0  0  0  0  0  0  0  1  1  0  0  0;
0  0  1  0  0  1  0  0  1  0  0  0  0  0;
0  0  1  0  0  0  1  0  1  0  0  0  0  0];

M = [0  1  0  0  1  0  0;
1  0  0  0  1  0  0;
0  0  0  1  0  1  1;
0  0  1  0  0  1  1;
1  1  0  0  0  0  0;
0  0  1  1  0  0  1;
0  0  1  1  0  1  0];

matrix = {[H],[J],[M]};

for p = 1:max(size(matrix))
  matrix{p}
  f = 10.^10;
  [V,D] = eig(matrix{p});
  eigValues = round(eig(matrix{p}));
  lambdaMax = max(max(eigValues));
  lambdaMin = min(min(eigValues));
  eigVectors = V;

  a = unique(eigValues);
  spectrum = [a,histc(eigValues(:),a)];
  %isRegular ??
  isRegular = 0;
  if all(floor(matrix{p} * ones(size(V),1)) == floor(lambdaMax*ones(size(V),1)) == 1)
      isRegular = 1;
  endif
  if(isRegular == 1)
    isRegular
  endif

  %regularDegree ??
  if (isRegular == 1)
    regularDegree = round(lambdaMax)
  endif

  %isConnected ??
  maxAbs = max(max(abs(eigValues)));
  for i = 1:size(eigValues)
    if eigValues(i) == maxAbs
      vector = eigVectors(1:size(eigVectors),i);
    endif
  endfor
  isConnected = 1;
  for i = 1:size(vector)
    if (vector(i) <= 0)
      isConnected = 0;
      break;
    endif
  endfor
  if (isConnected == 1)
    isConnected
  endif

  %isBipart ??
  isBipart = 0;
  if all((spectrum(1:size(spectrum),2) == flip(spectrum(1:size(spectrum),2))))
    isBipart = 1;
  endif
  if (isBipart == 1)
    isBipart
  endif

  %isLinear ??
  isLinear = 0;
  if (lambdaMin + 2 >= 0)
    isLinear = 1;
  endif
  if (isLinear == 1)
    isLinear
  endif
  
  %Majorante
  minorante = ceil(-(lambdaMax/lambdaMin) + 1)
  majorante = floor(lambdaMax + 1)
endfor
