import './Landing.css'

const services = [
  ['01', 'Strategija', 'Aiški kryptis, kuri sujungia jūsų verslo tikslus ir žmonių poreikius.'],
  ['02', 'Dizainas', 'Išgryninta vizualinė kalba ir patirtys, kurias norisi prisiminti.'],
  ['03', 'Įgyvendinimas', 'Greitas, atsakingas kūrimas nuo pirmos idėjos iki paleidimo.'],
]

function Arrow({ className = '' }) { return <span className={`arrow ${className}`} aria-hidden="true">↗</span> }

export default function Landing() {
  return <main>
    <nav className="nav container" aria-label="Pagrindinė navigacija">
      <a className="brand" href="#pradzia" aria-label="Forma pradžia">forma<span>.</span></a>
      <div className="nav-links"><a href="#paslaugos">Paslaugos</a><a href="#darbai">Darbai</a><a href="#kontaktai">Kontaktai</a></div>
      <a className="nav-cta" href="#kontaktai">Pradėkime <Arrow /></a>
    </nav>
    <section className="hero container" id="pradzia">
      <p className="eyebrow">Kūrybinė skaitmeninė studija · Vilnius</p>
      <div className="hero-grid"><h1>Idėjos, kurios<br /><em>juda pirmyn.</em></h1><div className="hero-side"><p>Kuriame išskirtines skaitmenines patirtis ambicingiems prekių ženklams ir žmonėms, kurie vertina formą.</p><a className="text-link" href="#darbai">Atrasti mūsų darbus <Arrow /></a></div></div>
      <div className="hero-visual" aria-label="Abstrakti studijos kompozicija"><div className="shape orb"></div><div className="shape arch"></div><div className="visual-label top-label">FORM / FUNCTION</div><div className="visual-label bottom-label">2026 © FORMA STUDIO</div><div className="hero-statement">Less noise.<br />More meaning.</div></div>
    </section>
    <section className="intro container"><p className="section-number">( 01 )</p><div><p className="intro-copy">Geras dizainas nesistengia būti garsiausias kambaryje. Jis sukuria erdvę tam, kas iš tiesų svarbu.</p><a className="round-link" href="#paslaugos" aria-label="Susipažinti su mūsų požiūriu"><Arrow /></a></div></section>
    <section className="services container" id="paslaugos"><div className="section-heading"><p className="eyebrow">Mūsų požiūris</p><h2>Nuo pirmo klausimo iki aiškaus atsakymo.</h2></div><div className="service-list">{services.map(([number, title, text]) => <article className="service" key={number}><p className="service-number">{number}</p><h3>{title}</h3><p>{text}</p><Arrow className="service-arrow" /></article>)}</div></section>
    <section className="work" id="darbai"><div className="container work-heading"><p className="eyebrow">Atrinkti darbai</p><a className="text-link" href="#kontaktai">Pamatyti daugiau <Arrow /></a></div><div className="work-grid container"><article className="project project-solis"><div className="project-art"><span>SOLIS</span><i></i></div><div className="project-meta"><h3>Solis / Identitetas</h3><p>2025</p></div></article><article className="project project-nida"><div className="project-art"><span>NIDA<br />HOUSE</span><b></b></div><div className="project-meta"><h3>Nida House / Skaitmeninė erdvė</h3><p>2025</p></div></article></div></section>
    <section className="contact" id="kontaktai"><div className="container contact-inner"><p className="eyebrow">Turite idėją?</p><h2>Padarykime jai<br /><em>formą.</em></h2><a className="contact-button" href="mailto:labas@forma.studio">labas@forma.studio <Arrow /></a></div></section>
    <footer className="footer container"><a className="brand" href="#pradzia">forma<span>.</span></a><p>© 2026 Forma Studio</p><p>Vilnius, Lietuva</p></footer>
  </main>
}
