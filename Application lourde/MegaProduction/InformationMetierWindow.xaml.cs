using System;
using System.Collections.Generic;
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
using MegaProductionDBLIB;
using System.Collections.ObjectModel;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationMetierWindow.xaml
    /// </summary>
    public partial class InformationMetierWindow : Window
    {
        private MegaCastingsEntities db;
        public Metier Metier { get; set; }
        public ObservableCollection<DomaineMetier> DomaineMetiers { get; set; }

        public InformationMetierWindow(MegaCastingsEntities context, Metier metier)
        {
            InitializeComponent();
            db = context;
            this.Metier = metier;
            this.DomaineMetiers = new ObservableCollection<DomaineMetier>(db.DomaineMetiers.ToList());
            this.DataContext = this;
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            if(this.Metier.Libelle == null)
            {
                MessageBox.Show("Veuillez remplir le libelle");
            }
            else
            {
                this.Metier.DomaineMetier = listDomaines.SelectedItem as DomaineMetier;
                this.DialogResult = true;
            }
        }
    }
}
