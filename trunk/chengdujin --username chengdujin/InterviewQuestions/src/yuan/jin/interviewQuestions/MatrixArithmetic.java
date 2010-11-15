package yuan.jin.interviewQuestions;

/**
 * http://www.dreamincode.net/code/snippet675.htm
 * 
 * @author Yuan
 * 
 */
public class MatrixArithmetic {
	public static double[][] crossProduct(double[][] a, double[][] b)
			throws IllegalArgumentException {
		int m1 = 0;
		int n1 = 0;
		int m2 = 0;
		int n2 = 0;

		try {
			m1 = a.length;
			n1 = a[0].length;
			m2 = b.length;
			n2 = b[0].length;

			if (m1 != m2 || n1 != n2) {
				throw new IllegalArgumentException(
						"The vector lengths do not match.");
			}
			if (m1 != 1 && n1 != 1 || m2 != 1 && n2 != 1) {
				throw new IllegalArgumentException(
						"The parameters are not vectors.");
			}
			if (m1 == 0 || n1 == 0 || m2 == 0 || n2 == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		if (m1 == 1) {
			if (n1 != 3) {
				throw new IllegalArgumentException(
						"The parameters are not vectors.");
			}
			return new double[][] { { a[0][1] * b[0][2] - a[0][2] * b[0][1],
					a[0][2] * b[0][0] - a[0][0] * b[0][2],
					a[0][0] * b[0][1] - a[0][1] * b[0][0] } };
		} else {
			if (m2 != 3) {
				throw new IllegalArgumentException(
						"The parameters are not vectors.");
			}
			return new double[][] { { a[1][0] * b[2][0] - a[2][0] * b[1][0] },
					{ a[2][0] * b[0][0] - a[0][0] * b[2][0] },
					{ a[0][0] * b[1][0] - a[1][0] * b[0][0] } };
		}
	}

	public static double[][] addition(double[][] A, double[][] B)
			throws IllegalArgumentException {
		int m = 0;
		int n = 0;

		try {
			m = A.length;
			n = A[0].length;

			if (m != B.length || n != B[0].length) {
				throw new IllegalArgumentException(
						"Matrix dimensions don't match.");
			}
			if (m == 0 || n == 0 || B.length == 0 || B[0].length == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		double[][] M = new double[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				M[i][j] = A[i][j] + B[i][j];
			}
		}

		return M;
	}

	public static double magnitude(double[][] a)
			throws IllegalArgumentException {
		int m = 0;
		int n = 0;

		try {
			m = a.length;
			n = a[0].length;

			if (m == 0 || n == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
			if (m != 1 && n != 1) {
				throw new IllegalArgumentException(
						"The parameter is not a vector.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		double result = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result += Math.pow(a[i][j], 2);
			}
		}

		return Math.sqrt(result);
	}

	public static double[][] adjoint(double[][] A)
			throws IllegalArgumentException {
		return scale(determinant(A), inverse(A));
	}

	public static double[][] scale(double k, double[][] B)
			throws IllegalArgumentException {
		int m = 0;
		int n = 0;

		try {
			m = B.length;
			n = B[0].length;

			if (m == 0 || n == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		double[][] A = new double[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = k * B[i][j];
			}
		}

		return A;
	}

	public static double determinant(double[][] B)
			throws IllegalArgumentException {
		try {
			if (B.length != B[0].length) {
				throw new IllegalArgumentException("Not a square matrix.");
			}
			if (B.length == 0 || B[0].length == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		int n = B.length;
		double[][] E = identity(n);
		double[] swap = new double[n];
		double determ = 1;

		double[][] A = transpose(transpose(B));

		for (int j = 0; j < n; j++) {
			for (int i = j; i < n; i++) {
				if (i == j) {
					if (A[i][j] == 0) {
						for (int k = j + 1; k < n; k++) {
							if (A[k][j] != 0) {
								swap = A[i];
								A[i] = A[k];
								A[k] = swap;
								determ *= -1;
								k = n;
							}
						}
					}
					determ *= A[i][j];
				} else {
					E[i][j] = -A[i][j] / A[j][j];
				}
				A = multiply(E, A);
				E = identity(n);
			}
		}

		return determ;
	}

	public static double[][] inverse(double[][] B)
			throws IllegalArgumentException {
		if (determinant(B) == 0) {
			throw new IllegalArgumentException("This matrix is singular.");
		}

		int n = B.length;
		double[][] V = identity(n);
		double[][] E = identity(n);
		double[] swap = new double[n];

		double[][] A = transpose(transpose(B));

		for (int j = 0; j < n; j++) {
			for (int i = j; i < n; i++) {
				if (i == j) {
					if (A[i][j] == 0) {
						for (int k = j + 1; k < n; k++) {
							if (A[k][j] != 0) {
								swap = A[i];
								A[i] = A[k];
								A[k] = swap;
								swap = V[i];
								V[i] = V[k];
								V[k] = swap;
								k = n;
							}
						}
					}
					E[i][j] = 1 / A[i][j];
				} else {
					E[i][j] = -A[i][j];
				}
				A = multiply(E, A);
				V = multiply(E, V);
				E = identity(n);
			}
		}
		for (int j = n - 1; j > 0; j--) {
			for (int i = j - 1; i > -1; i--) {
				E[i][j] = -A[i][j];
				A = multiply(E, A);
				V = multiply(E, V);
				E = identity(n);
			}
		}

		return V;
	}

	public static double[][] multiply(double[][] C, double[][] B)
			throws IllegalArgumentException {
		try {
			if (C[0].length != B.length) {
				throw new IllegalArgumentException(
						"Interior dimensions of multiplication are not equal.");
			}
			if (C.length == 0 || C[0].length == 0 || B.length == 0
					|| B[0].length == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		int m = C.length;
		int n = B[0].length;

		double[][] A = transpose(transpose(C));
		double[][] BT = transpose(B);
		double[][] M = new double[m][n];
		double[][] tempVect1 = new double[1][n];
		double[][] tempVect2 = new double[1][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				tempVect1[0] = A[i];
				tempVect2[0] = BT[j];
				M[i][j] = dotProduct(tempVect1, tempVect2);
			}
		}

		return M;
	}

	public static double dotProduct(double[][] a, double[][] b)
			throws IllegalArgumentException {
		int m1 = 0;
		int n1 = 0;
		int m2 = 0;
		int n2 = 0;

		try {
			m1 = a.length;
			n1 = a[0].length;
			m2 = b.length;
			n2 = b[0].length;

			if (m1 != m2 || n1 != n2) {
				throw new IllegalArgumentException(
						"The vector lengths do not match.");
			}
			if (m1 != 1 && n1 != 1 || m2 != 1 && n2 != 1) {
				throw new IllegalArgumentException(
						"The parameters are not vectors.");
			}
			if (m1 == 0 || n1 == 0 || m2 == 0 || n2 == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		double result = 0;

		for (int i = 0; i < m1; i++) {
			for (int j = 0; j < n1; j++) {
				result += a[i][j] * b[i][j];
			}
		}

		return result;
	}

	public static double[][] transpose(double[][] A)
			throws IllegalArgumentException {
		int m = 0;
		int n = 0;

		try {
			m = A.length;
			n = A[0].length;
			if (m == 0 || n == 0) {
				throw new IllegalArgumentException(
						"0 dimension matrices are non-existent.");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException(
					"0 dimension matrices are non-existent.");
		}

		double[][] T = new double[n][m];
		double temp = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp = A[i][j];
				T[j][i] = temp;
			}
		}

		return T;
	}

	public static double[][] identity(int n) throws IllegalArgumentException {
		if (n < 1) {
			throw new IllegalArgumentException(
					"The dimension must be greater than 0.");
		}

		double[][] I = new double[n][n];

		for (int i = 0; i < n; i++) {
			I[i][i] = 1;
		}

		return I;
	}
}
