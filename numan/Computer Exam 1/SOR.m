function x = SOR(A, b, omega, x0)
    
    x_k = x0;
 
 
 
    res_k = 1;
    
    D = diag(diag(A));
    L = tril(A, -1);
    
    M = omega * L  + D;
    
    while norm(res_k, inf)> 1e-11
        
        res_k = (b - A * x_k);
        y_k1 = M * x_k + res_k * omega;
        x_k = M \ y_k1;
        
        
    end
    x = x_k ;
 
end
