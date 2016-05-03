using MegaProductionDBLIB;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour TypeContratWindow.xaml
    /// </summary>
    public partial class TypeContratWindow : Window
    {
        private MegaCastingsEntities db;
        public ObservableCollection<TypeContrat> TypeContrats { get; set; }
        public TypeContrat typeContrat { get; set; }

        public TypeContratWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.TypeContrats = new ObservableCollection<TypeContrat>(db.TypeContrats.ToList());
            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            typeContrat = new TypeContrat();
            InformationTypeContratWindow informationTypeContratWindow = new InformationTypeContratWindow(db, typeContrat);

            if (informationTypeContratWindow.ShowDialog() == true)
            {
                db.TypeContrats.Add(informationTypeContratWindow.TypeContrat);
                this.TypeContrats.Add(informationTypeContratWindow.TypeContrat);
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            typeContrat = listTypeContrats.SelectedItem as TypeContrat;
            int currentIndex = listTypeContrats.SelectedIndex;
            InformationTypeContratWindow informationTypeContratWindow = new InformationTypeContratWindow(db, typeContrat);

            if (informationTypeContratWindow.ShowDialog() == true)
            {
                db.SaveChanges();
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listTypeContrats.SelectedItem != null)
            {
                typeContrat = listTypeContrats.SelectedItem as TypeContrat;
                int currentIndex = listTypeContrats.SelectedIndex;
                db.TypeContrats.Remove(typeContrat);
                this.TypeContrats.Remove(typeContrat);
                listTypeContrats.SelectedIndex = currentIndex;
                listTypeContrats.Focus();
                db.SaveChanges();
            }
        }
    }
}
