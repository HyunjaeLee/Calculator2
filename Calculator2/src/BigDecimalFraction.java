import java.math.BigDecimal;

public class BigDecimalFraction extends Fraction implements Comparable<BigDecimalFraction>{
	private static final long serialVersionUID = 3716956499850495638L;
	
	private final BigDecimal numerator;
	private final BigDecimal denominator;
	
	public BigDecimalFraction(BigDecimal numerator, BigDecimal denominator){
		if(denominator.equals(BigDecimal.ZERO)){
			throw new IllegalArgumentException("Denominator is zero");
		}
		this.numerator=numerator;
		this.denominator=denominator;
	}
	
	public BigDecimalFraction(BigDecimal numerator) {this(numerator, BigDecimal.ONE);}
	
	public BigDecimalFraction(String numerator, String denominator) {this(new BigDecimal(numerator), new BigDecimal(denominator));}
	
	public BigDecimalFraction(String numerator) {this(new BigDecimal(numerator), BigDecimal.ONE);}
	
	public BigDecimalFraction add(BigDecimalFraction val){return add(this, val);}
	
	private static BigDecimalFraction add(BigDecimalFraction fst, BigDecimalFraction snd){
		fst=reduce(fst);
		snd=reduce(snd);
		BigDecimalFraction[] val=lcd(fst, snd);
		return reduce(val[0].numerator.add(val[1].numerator), val[0].denominator);
	}
	
	public BigDecimalFraction subtract(BigDecimalFraction val){return subtract(this, val);}
	
	private static BigDecimalFraction subtract(BigDecimalFraction fst, BigDecimalFraction snd){
		fst=reduce(fst);
		snd=reduce(snd);
		BigDecimalFraction[] val=lcd(fst, snd);
		return reduce(val[0].numerator.subtract(val[1].numerator), val[0].denominator);
	}
	
	public BigDecimalFraction multiply(BigDecimalFraction val){return multiply(this, val);}
	
	private static BigDecimalFraction multiply(BigDecimalFraction fst, BigDecimalFraction snd){
		fst=reduce(fst);
		snd=reduce(snd);
		return reduce(fst.numerator.multiply(snd.numerator), fst.denominator.multiply(snd.denominator));
	}
	
	public BigDecimalFraction divide(BigDecimalFraction val){return divide(this, val);}
	
	private static BigDecimalFraction divide(BigDecimalFraction fst, BigDecimalFraction snd){return multiply(fst, snd.reciprocal());}
	
	public BigDecimalFraction reduce() {return reduce(numerator, denominator);}
	
	private static BigDecimalFraction reduce(BigDecimalFraction val) {return reduce(val.numerator, val.denominator);}
	
	private static BigDecimalFraction reduce(BigDecimal numerator, BigDecimal denominator){
		if(denominator.signum()==-1){
			numerator=numerator.negate();
			denominator=denominator.negate();
		}
		if(!(numerator.scale()==0))
			numerator=numerator.multiply(BigDecimal.TEN.pow(numerator.scale()));
		if(!(denominator.scale()==0))
			denominator=denominator.multiply(BigDecimal.TEN.pow(denominator.scale()));
		final BigDecimal gcd=gcd(numerator, denominator);
		return new BigDecimalFraction(numerator.divide(gcd), denominator.divide(gcd));
	}
	
	private static BigDecimal lcd(final BigDecimal xs, final BigDecimal ys){
		final BigDecimal gcd=gcd(xs, ys);
		return xs.divide(gcd).multiply(ys);
	}
	
	private static BigDecimalFraction[] lcd(final BigDecimalFraction fst, final BigDecimalFraction snd){
		final BigDecimal lcd=lcd(fst.denominator, snd.denominator);
		return new BigDecimalFraction[]{
				new BigDecimalFraction(fst.numerator.multiply(lcd.divide(fst.denominator)),lcd), 
				new BigDecimalFraction(snd.numerator.multiply(lcd.divide(snd.denominator)),lcd)
				};
	}
	
	private static BigDecimal gcd(final BigDecimal xs, final BigDecimal ys) {return new BigDecimal(xs.toBigInteger().gcd(ys.toBigInteger()).toString());}
	
	public BigDecimalFraction reciprocal() {return reciprocal(this);}
	
	private static BigDecimalFraction reciprocal(final BigDecimalFraction val) {return new BigDecimalFraction(val.denominator, val.numerator);}
	
	public BigDecimalFraction abs(){return new BigDecimalFraction(this.numerator.abs(), this.denominator.abs());}
	
	@Override
	public int compareTo(BigDecimalFraction o) {
		BigDecimalFraction xs=reduce(this);
		BigDecimalFraction ys=reduce(o);
		final BigDecimalFraction[] val=lcd(xs, ys);
		return val[0].numerator.compareTo(val[1].numerator);
	}

	@Override
	public String toString() {
		return numerator+" / "+denominator;
	}
}
