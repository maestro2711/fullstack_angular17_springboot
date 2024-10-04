describe('Footer Section',():void=>{
  beforeEach(():void=>{
    cy.visit('/');
  })

  it('should display the correct footer text', ():void => {
    cy.get('app-foooter').within(():void=>{
      cy.get('p-divider').should('exist');
      cy.get('p').contains('© 2024 AfroGeek').should('be.visible')
      cy.get('p').contains('GeeHR-Connect ').should('be.visible')
    })

  });
})
